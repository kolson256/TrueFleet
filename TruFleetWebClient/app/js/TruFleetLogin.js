'use strict';

var app=angular.module('TruFleetLoginApp', ['ngRoute']);

app.
    config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/login', {templateUrl: 'partials/login.html',controller:'loginCtrl'});
        $routeProvider.when('/main', {templateUrl: 'partials/main.html',controller:'mainCtrl'});
        $routeProvider.otherwise({redirectTo: '/login'});
    }]);
