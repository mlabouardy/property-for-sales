'use strict';

/**
 * @ngdoc function
 * @name clientApp.controller:LogoutCtrl
 * @description
 * # LogoutCtrl
 * Controller of the clientApp
 */
angular.module('clientApp')
  .controller('LogoutCtrl', function (Authentication, $location) {
  	 Authentication.logout();
     $location.path('/');
  });
