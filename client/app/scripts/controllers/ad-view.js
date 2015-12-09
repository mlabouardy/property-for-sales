'use strict';

/**
 * @ngdoc function
 * @name clientApp.controller:AdViewCtrl
 * @description
 * # AdViewCtrl
 * Controller of the clientApp
 */
angular.module('clientApp')
  .controller('AdViewCtrl', function ($routeParams, $scope, Adverts) {
    Adverts.findById($routeParams.id).success(function(data){
    	$scope.advert=data;
    })
  });
