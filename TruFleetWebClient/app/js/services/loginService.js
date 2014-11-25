'use strict';

app.factory('loginService', function($http, $window, $location) {
    return {
        login:function(user, scope) {
            $http({
                method: 'POST',
                url: 'http://localhost:8080/Login',
                headers: { },
                data: {
                    username: user.username,
                    password: user.password
                }
            })
                .success(function(data) {
                    scope.loginResult = data;
                    $window.sessionStorage.setItem('authenticationToken', data.authenticationToken);
                    $window.sessionStorage.setItem('apiVersion', data.apiVersion);
                    $window.sessionStorage.setItem('tenantId', data.tenantId);
                    $location.path("/main");
                })
                .error(function(data) {
                    scope.errorResult = "There was an error.";
                });
        }
    }
});
