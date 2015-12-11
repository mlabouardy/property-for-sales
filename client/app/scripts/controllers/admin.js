'use strict';

/**
 * @ngdoc function
 * @name clientApp.controller:AdminCtrl
 * @description
 * # AdminCtrl
 * Controller of the clientApp
 */
angular.module('clientApp')
  .controller('AdminCtrl', function (Authentication, $location, $scope, User) {
    if(Authentication.isConnected() && Authentication.getRole()=='ROLE_ADMIN'){
    	User.getUsers().success(function(data){
    		$scope.users=data;
    	});
    }else{
    	$location.path('/login');
    }
  });
