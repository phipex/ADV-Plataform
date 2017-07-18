'use strict';

// Declare app level module which depends on views, and components
var app = angular.module('myApp', [
    'ngRoute',
    'myApp.view1',
    'myApp.view2',
    'myApp.version',
    'ui.bootstrap',
    'ngWebSocket',
    'angularScreenfull'
]).
config(['$locationProvider', '$routeProvider', function($locationProvider, $routeProvider) {
    $locationProvider.hashPrefix('!');

    $routeProvider.otherwise({redirectTo: '/view1'});
}]);

app.constant('config', {
    appName: 'Apuestas Deportivas',
    appVersion: 0.1,
    apiUrl: 'http://www.google.com?api'
});

app.service('WplayUsuarioService',WplayUsuarioService);

WplayUsuarioService.$inject = ['$http','$q'];

function WplayUsuarioService($http,$q) {
    var urlServer = 'https://aliados.wplay.co/actions/punto-venta/recargas.php/recargas';

    return {
        consultaUsuario:consultaUsuario

    };

    function consultaUsuario(cedula) {
        console.log(cedula);
        var objConsulta = {
                "id_punto": "23",
                "token_seguridad": "ODk2NDBiNjBjOGZiMjkyOGFlZmZiYThi%2Fd41d8cd98f00b204e9800998ecf8427e%2Fe7c9a98d853f6d3d4df263eefc0fba3d",
                "cedula": cedula,
                "consulta": "consulta"
            },
            deferred = $q.defer();


        function onConsultsUsuarioSucces(response) {
            var rawData = response.data,
                data = rawData.data                ;
            console.log(response);
            console.log(rawData);
            if(data){
                var nombre = data.nombre;
                deferred.resolve(nombre);
            }else{
                deferred.reject({error: "Nombre no encontrado"});
            }



        }
        function onConsultaUsuarioFail(error) {
            deferred.reject(error.error);
        }

        $http.post(urlServer,objConsulta).then(
            onConsultsUsuarioSucces,
            onConsultaUsuarioFail
        );

        return deferred.promise;

    }

}

app.factory('Billetero', ['$websocket', function factoryBilletero($websocket) {
    var dataStream = null,//$websocket('ws://localhost:8282/BackendBingo-web/websocket/tableros'), // Open a WebSocket connection
        collection = [],
        stateDic=[
            "CONNECTING",
            "OPEN",
            "CLOSING",
            "CLOSED"
        ],
        state = {
            codigo: stateDic.OPEN,
            nombre: "OPEN"

        },
        callbackOnly = null;



    function openConection(){
        console.log("WebSocket::openConection",dataStream);
        console.log((dataStream === null || dataStream && dataStream.readyState === 3 ),(dataStream && dataStream.readyState == 3))
        if (dataStream === null || dataStream && dataStream.readyState === 3 ) {
            console.log("WebSocket:: inicial");
            dataStream = $websocket('ws://localhost:9876');
            dataStream.onMessage(function dataStreamonMessage(message) {
                collection.push(JSON.parse(message.data));
                //console.log("onMessage",message);
                if (callbackOnly) {
                    //console.log("callbackOnly");
                    callbackOnly(message);
                }


            });
        }
        /*dataStream.onOpen(function (message) {

         //console.log(message);
         });
         dataStream.onClose(function (message) {

         //console.log(message);
         });*/
    }

    function desactivarBilletero() {
        if(dataStream){
            dataStream.send('off');
        }
    }

    function activarBilletero() {
        if(dataStream){
            dataStream.send('on');
        }
    }


    openConection();


    return {
        desactivarBilletero: desactivarBilletero,
        activarBilletero: activarBilletero,
        dataStream: dataStream,
        open: openConection,
        stateDic: stateDic,
        collection: collection,
        state: function () {
            var state = (dataStream !== null)?dataStream.readyState:3;
            return state || 3;
        },
        addListener: function (callback) {
            console.log("addListener---------------");
            if (angular.isFunction(callback) && !callbackOnly) {
                console.log("        addListener---------------");
                callbackOnly = callback;
            }
        },
        get: function () {//TODO no se para que sirve
            dataStream.send(JSON.stringify({ action: 'get' }));
        }
    };


}]);


app.service('WplayRecargaService',WplayRecargaService);

WplayUsuarioService.$inject = ['$http','$q'];

function WplayRecargaService($http,$q) {
    var urlServer = 'http://190.0.24.228:8383/recargas/api/recargas';
    //var urlServer = 'http://localhost:8585/api/recargas';
    //var urlServer = 'http://190.0.24.228:8585/api/recargas';

    return {
        recarga:recarga

    };

    function recarga(cedula,valorRecarga) {
        console.log(cedula);
        var objConsulta = {
                "cliente": cedula+"",
                "valorRecarga": valorRecarga,
                "idPuntoRecarga": "Cabina 1",
                "estadoRecarga": "PENDIENTE",
                "partner": "WPLAY",
                "horaRecarga": "2017-06-02T06:01:00.000Z"
            },
            deferred = $q.defer();


        function onConsultsUsuarioSucces(response) {
            var rawData = response.data,
                data = rawData.data;
            console.log("response post",response);
            console.log(rawData);
            if(rawData){
                var nombre = rawData.nombre;
                deferred.resolve(nombre);
            }else{
                deferred.reject({error: "Nombre no encontrado"});
            }



        }
        function onConsultaUsuarioFail(error) {
            console.log("error",error);
            deferred.reject(error.error);
        }

        console.log(urlServer);
        console.log(JSON.stringify(objConsulta));

        $http.post(urlServer,objConsulta).then(
            onConsultsUsuarioSucces,
            onConsultaUsuarioFail
        );

        return deferred.promise;

    }

}


app.controller('ControllerHome', ControllerHome);

ControllerHome.$inject = ['$scope','$rootScope','config','WplayUsuarioService','Billetero','WplayRecargaService','$uibModal','$timeout'];

function ControllerHome($scope,$rootScope,config,WplayUsuarioService,Billetero,WplayRecargaService, $uibModal, $timeout) {
    var vm = this,
        home = this;

    // modelos
    home.appName = config.appName;
    home.cedula = null;
    home.cliente = null;
    home.hayLogin = false;
    home.apuesta = null;
    home.modal = {
        titulo: null,
        body: null
    };

    //eventos
    home.onclickIngresar = onclickIngresar;
    home.onClickRecargar = onClickRecargar;
    home.onOut = onOut;
    home.onClickKeyNum = onClickKeyNum;
    home.addRecarga = addRecarga;

    home.modalLoading = null;


    $scope.WebSocket = Billetero;
    $scope.MyData = Billetero.collection;
    var stateDic = Billetero.stateDic,
        keyStates = Object.keys(stateDic);
    /**
     * estado actual de la coneccion con el billetero
     * @type {null}
     */
    $scope.estadoActual = null;
    $scope.detailBilletero = null;
    $scope.estado = Billetero.state;
    $scope.open = function () {
        if ($scope.estado !== 'OPEN') {
            Billetero.open();
        }

    };
    Billetero.open();
    var onMensaje = function (mesageEvent) {
        //console.log("++++++++++");
        //var _this = this;
        //console.log("_this",_this);
        var objetoMsg = JSON.parse(mesageEvent.data);
        console.log("objetoMsg", objetoMsg);

        //console.log(mesageEvent);
        //console.log(WebSocket);

        $timeout(function timeoutrecarga() {
            console.log("mensaje recibido", objetoMsg);
            gestionMensajeBilletero(objetoMsg);
            //console.log($scope.recarga);
            //addRecarga(objetoMsg.valor);

            //mostrarRecarga(acciones.recarga.valor);
            //console.log($scope.recarga.valor+'');
        }, 500);
    };

    function gestionMensajeBilletero(objetoMsg) {

        if (objetoMsg.error) {
            var mensaje = objetoMsg.detail;
            $scope.detailBilletero = mensaje;
            return;
            //openModal('Error en el billetero', mensaje);
        }
        if (objetoMsg.advice) {
            var detail = objetoMsg.detail;
            $scope.detailBilletero = detail;
            return;
        }
        if (isNumeric(objetoMsg)) {
            addRecarga(objetoMsg);
        } else {
            console.log("NO SE CUANDO PASA", objetoMsg);
        }


    }

    function isNumeric(n) {
        return !isNaN(parseFloat(n)) && isFinite(n);
    }

    //console.log("control.addListener");
    Billetero.addListener(onMensaje);
    $scope.$watch("estado()", function watchestado() {
        var estadoActual = $scope.estado();
        $scope.estadoActual = stateDic[estadoActual];
    });

    function addRecarga(valor) {
        //acciones.recarga.valor += valor;
        //console.log($scope.recarga.valor+'');
        home.apuesta += valor;
        mostrarRecarga(valor);
        //console.log($scope.recarga.valor+'');
    }

    function mostrarRecarga(valor) {

        openNuevoBilleteModal(valor)
        //openModal('Nuevo Billete', 'Un nuev billete de $' + valor + ' pesos a sido ingresado, desea ingresar otro billete o realizar la recarga?');
    }

    function onOut() {
        home.hayLogin = false;
        home.cedula = null;
        home.apuesta = null;
        home.cliente = null;
        Billetero.desactivarBilletero();
        $scope.detailBilletero = null;
    }

    function onclickIngresar() {
        console.log(home.cedula);
        if (home.cedula) {

            openLoadModal();
            var encontrado = function encontrado(nombre) {
                    //ingresar
                    console.log(nombre);
                    home.hayLogin = true;
                    home.cliente = nombre;
                    //activar billetero
                    Billetero.activarBilletero();
                    openModalLogin(nombre);
                    home.modalLoading.close();

                },
                error = function error(error) {
                    home.modalLoading.close();
                    Billetero.desactivarBilletero();
                    openModal('Error al encontrar el usuario', 'La cedula ingresada no está inscrita en nuestro sistema');
                    onOut();
                };

            WplayUsuarioService.consultaUsuario(home.cedula)
                .then(encontrado, error);
        } else {
            //sacar mensaje de error de que la cedula es obligatoria
            Billetero.desactivarBilletero();
            openModal('Error al ingresar', 'Error al ingresar a la aplicación');
            onOut();
        }


    }

    function onClickRecargar() {
        if (home.apuesta) {
            openLoadModal();
            var recargado = function (data) {
                    home.modalLoading.close();
                    Billetero.desactivarBilletero();
                    openModal('Recarga Exitosa', 'Su recarga ha sido realizada exitosamente');
                    onOut();

                },
                errorRecarga = function (error) {
                    //Billetero.desactivarBilletero();
                    home.modalLoading.close();
                    openModal('Error en la recarga', 'La recarga no pudo ser realizada exitosamente');
                    onOut();
                };
            WplayRecargaService.recarga(home.cedula, home.apuesta)
                .then(recargado, errorRecarga);

        }else{
            openModal('Error en la recarga', 'Debe ingresar billetes para realizar la recarga');
            onOut();
        }
    }

    function openModal(titulo, body) {


        var modalInstance = $uibModal.open({
            templateUrl: 'myModal.html',
            controller: function ($scope, titulo, body) {
                $scope.titulo = titulo;
                $scope.body = body;
                $scope.ok=function () {
                    $scope.$dismiss();
                }

                /* $scope.cancel = function ()
                 {
                 $modalInstance.dismiss('cancel');
                 };*/
            },
            //size: size,
            resolve: {
                titulo: function () //scope del modal
                {
                    return titulo;
                },
                body: function () {
                    return body;
                }
            },
            windowClass: 'center-modal'
        });
    }

    function openNuevoBilleteModal(valorNuevo) {


        var modalInstance = $uibModal.open({
            templateUrl: 'nuevoBillete.html',
            controller: function ($scope, valorNuevo, total) {
                console.log("$scope",$scope);

                $scope.nuevoBillete = valorNuevo;
                $scope.total = total;


                $scope.ok = function () {
                    $scope.$close();
                };

                $scope.cancel = function () {
                    $scope.$dismiss('cancel');
                };
            },
            //size: size,
            resolve: {
                valorNuevo: function () //scope del modal
                {
                    return valorNuevo;
                },
                total: function () {
                    return home.apuesta;
                }
            },
            windowClass: 'center-modal'
        });

        modalInstance.result.then(function () {
            home.onClickRecargar();
        });
    }

    function openLoadModal() {


        home.modalLoading = $uibModal.open({
            templateUrl: 'loadingModal.html',
            animation: false,
            windowClass: 'center-modal'

        });
    }

    function openModalLogin(nombre) {


        var modalInstance = $uibModal.open({
            templateUrl: 'loginModal.html',
            controller: function($scope,nombre)
            {
                console.log("this",this);
                console.log("$scope",$scope);


                $scope.nombre = nombre;

                console.log("modela::modalInstance",modalInstance);

                $scope.ok=function ok() {
                    $scope.$dismiss();

                };
                $scope.cancel=function cancel() {
                    $scope.$close();
                    home.onOut();
                }

                /* $scope.cancel = function ()
                 {
                 $modalInstance.dismiss('cancel');
                 };*/
            },
            //size: size,
            resolve: {
                nombre: function() //scope del modal
                {
                    return nombre;
                }
            },
            windowClass: 'center-modal'
        });

        console.log("modalInstance",modalInstance);
    }

    function onClickKeyNum(numKey) {
        if(numKey === -2){
            home.cedula = null;
            return;
        }
        if((home.cedula) && home.cedula.length > 0 && numKey === -1){
            var cedula = home.cedula;
            home.cedula = cedula.substring(0,cedula.length - 1);

        }else if(home.cedula) {
            home.cedula = home.cedula + "" + numKey;
        } else{
            home.cedula = numKey;
        }

    }

}
