'use strict';

/**
 * @ngdoc function
 * @name clientApp.controller:UserAdvertsCtrl
 * @description
 * # UserAdvertsCtrl
 * Controller of the clientApp
 */
angular.module('clientApp')
  .controller('UserAdvertsCtrl', function (Adverts, $scope, $routeParams) {
    Adverts.getAdvertsByUserId($routeParams.id).success(function(adverts){
    	$scope.adverts=adverts;
    });
  });
