'use strict';

/**
 * @ngdoc function
 * @name clientApp.controller:RegisterCtrl
 * @description
 * # RegisterCtrl
 * Controller of the clientApp
 */
angular.module('clientApp')
  .controller('RegisterCtrl', function (Authentication, $location, $scope) {
    if(Authentication.isConnected()){
    	$location.path('/dashboard');
    }else{
    	$scope.register=function(){
    		var data={
    			firstName: $scope.firstName,
    			lastName: $scope.lastName,
    			email: $scope.email,
    			password: $scope.password,
    			address: "",
    			phone: ""
    		};

    		console.log(data);
    	}
    }
  });
