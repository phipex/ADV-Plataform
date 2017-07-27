package co.com.ies.adv.backend.cabinas.web.rest;

import co.com.ies.adv.backend.cabinas.domain.Authority;
import co.com.ies.adv.backend.cabinas.domain.User;
import co.com.ies.adv.backend.cabinas.security.AuthoritiesConstants;
import co.com.ies.adv.backend.cabinas.security.jwt.JWTConfigurer;
import co.com.ies.adv.backend.cabinas.security.jwt.TokenProvider;
import co.com.ies.adv.backend.cabinas.service.UserService;
import co.com.ies.adv.backend.cabinas.web.rest.vm.LoginVM;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

/**
 * Controller to authenticate users.
 */
@RestController
@RequestMapping("/api")
public class UserJWTController {

    private final Logger log = LoggerFactory.getLogger(UserJWTController.class);

    private final TokenProvider tokenProvider;

    private final AuthenticationManager authenticationManager;
    
    private final UserService userService;

    public UserJWTController(TokenProvider tokenProvider, AuthenticationManager authenticationManager, UserService userService) {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping("/authenticate")
    @Timed
    public ResponseEntity authorize(@Valid @RequestBody LoginVM loginVM, HttpServletResponse response) {

        UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(loginVM.getUsername(), loginVM.getPassword());

        try {
        	verificarCabina(loginVM.getUsername());
        	
        	Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            boolean rememberMe = (loginVM.isRememberMe() == null) ? false : loginVM.isRememberMe();
            String jwt = tokenProvider.createToken(authentication, rememberMe);
            response.addHeader(JWTConfigurer.AUTHORIZATION_HEADER, "Bearer " + jwt);
            return ResponseEntity.ok(new JWTToken(jwt));
        } catch (AuthenticationException ae) {
            log.trace("Authentication exception trace: {}", ae);
            return new ResponseEntity<>(Collections.singletonMap("AuthenticationException",
                ae.getLocalizedMessage()), HttpStatus.UNAUTHORIZED);
        }
    }

    public void verificarCabina(String login){
    	
    	Optional<User> userWithAuthorities = userService.getUserWithAuthoritiesByLogin(login);
    	
    	log.debug(userWithAuthorities.toString());
    	
    	boolean isUserpresent = userWithAuthorities.isPresent();
		
    	if (!isUserpresent) {
    		return;
		}
    	
    	User user = userWithAuthorities.get();
		
    	Set<Authority> authorities = user.getAuthorities();
    	
    	log.debug(authorities.toString());
    	
    	Authority authorityCabina = new Authority();
    	
    	authorityCabina.setName(AuthoritiesConstants.CABINA);
    	
    	boolean containsCabina = authorities.contains(authorityCabina);
    	
    	log.debug("!!!!!!!es cabina"+containsCabina);
    	
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
