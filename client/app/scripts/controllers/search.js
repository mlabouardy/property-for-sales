'use strict';

/**
 * @ngdoc function
 * @name clientApp.controller:SearchCtrl
 * @description
 * # SearchCtrl
 * Controller of the clientApp
 */
angular.module('clientApp')
  .controller('SearchCtrl', function ($scope, Adverts) {
     Adverts.getAdverts().success(function(data){
     	$scope.advertsè=data;
    });
  });
