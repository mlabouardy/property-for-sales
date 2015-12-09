'use strict';

/**
 * @ngdoc function
 * @name clientApp.controller:DashboardCtrl
 * @description
 * # DashboardCtrl
 * Controller of the clientApp
 */
angular.module('clientApp')
  .controller('DashboardCtrl', function (Authentication, $location) {
    if(!Authentication.isConnected()){
    	$location.path('/login');
    }
  });
