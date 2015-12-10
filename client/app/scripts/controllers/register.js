'use strict';

/**
 * @ngdoc function
 * @name clientApp.controller:RegisterCtrl
 * @description
 * # RegisterCtrl
 * Controller of the clientApp
 */
angular.module('clientApp')
  .controller('RegisterCtrl', function (Authentication, $location, $scope, User) {
    if(Authentication.isConnected()){
    	$location.path('/dashboard');
    }else{
    	$scope.register=function(){
    		User.register($scope.firstName, $scope.lastName, $scope.email, $scope.password)
    			.success(function(data){
    				$location.path('/login');
    			})
    			.error(function(){
    				$scope.error="Error";
    			});
       
    	}
    }
  });
