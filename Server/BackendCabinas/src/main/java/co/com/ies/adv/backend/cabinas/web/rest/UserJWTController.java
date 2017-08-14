package co.com.ies.adv.backend.cabinas.web.rest;

import java.util.Collections;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.annotation.JsonProperty;

import co.com.ies.adv.backend.cabinas.domain.EnumError;
import co.com.ies.adv.backend.cabinas.domain.core.exceptions.CabinaException;
import co.com.ies.adv.backend.cabinas.security.jwt.JWTConfigurer;
import co.com.ies.adv.backend.cabinas.security.jwt.TokenProvider;
import co.com.ies.adv.backend.cabinas.service.CabinaService;
import co.com.ies.adv.backend.cabinas.service.UserService;
import co.com.ies.adv.backend.cabinas.web.rest.errors.ErrorVM;
import co.com.ies.adv.backend.cabinas.web.rest.vm.LoginVM;

/**
 * Controller to authenticate users.
 */
@RestController
@RequestMapping("/api")
public class UserJWTController {

    private final Logger log = LoggerFactory.getLogger(UserJWTController.class);

    private final TokenProvider tokenProvider;

    private final AuthenticationManager authenticationManager;
    
    private final CabinaService cabinaService;

    public UserJWTController(TokenProvider tokenProvider, AuthenticationManager authenticationManager, UserService userService, CabinaService cabinaService) {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
        this.cabinaService = cabinaService;
    }

    @PostMapping("/authenticate")
    @Timed
    public ResponseEntity authorize(@Valid @RequestBody LoginVM loginVM, HttpServletResponse response) {

        UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(loginVM.getUsername(), loginVM.getPassword());

        try {
        	
        	
        	
        	Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            cabinaService.loginCabina(loginVM.getUsername());
            
            boolean rememberMe = (loginVM.isRememberMe() == null) ? false : loginVM.isRememberMe();
            String jwt = tokenProvider.createToken(authentication, rememberMe);
            response.addHeader(JWTConfigurer.AUTHORIZATION_HEADER, "Bearer " + jwt);
            return ResponseEntity.ok(new JWTToken(jwt));
        }catch (CabinaException ae) {
        	EnumError enumError = ae.getEnumError();
        	ErrorVM cabinaError = new ErrorVM(enumError);
            log.trace("Authentication exception trace: {}", ae);
            return new ResponseEntity<>(Collections.singletonMap("CabinaException",
            		cabinaError), HttpStatus.UNAUTHORIZED);
        }catch (AuthenticationException ae) {
            log.trace("Authentication exception trace: {}", ae);
            return new ResponseEntity<>(Collections.singletonMap("AuthenticationException",
                ae.getLocalizedMessage()), HttpStatus.UNAUTHORIZED);
        }
    }
    
    /**
     * Object to return as body in JWT Authentication.
     */
    static class JWTToken {

        private String idToken;

        JWTToken(String idToken) {
            this.idToken = idToken;
        }

        @JsonProperty("id_token")
        String getIdToken() {
            return idToken;
        }

        void setIdToken(String idToken) {
            this.idToken = idToken;
        }
    }
}
