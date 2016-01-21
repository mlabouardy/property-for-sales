'use strict';

/**
 * @ngdoc function
 * @name clientApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the clientApp
 */
angular.module('clientApp')
  .controller('MainCtrl', function (Adverts, $scope) {
  	Adverts.mostRecents().success(function(data){
     	$scope.adverts=data;
     });
  });
