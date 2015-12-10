'use strict';

/**
 * @ngdoc function
 * @name clientApp.controller:DashboardCtrl
 * @description
 * # DashboardCtrl
 * Controller of the clientApp
 */
angular.module('clientApp')
  .controller('DashboardCtrl', function (Authentication, $location, $scope, User) {
    if(!Authentication.isConnected()){
    	$location.path('/login');
    }else{
    	User.getUsers().success(function(data){
    		$scope.users=data;
    	});
    }
  });
