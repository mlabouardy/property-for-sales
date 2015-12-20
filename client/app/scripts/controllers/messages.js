'use strict';

/**
 * @ngdoc function
 * @name clientApp.controller:MessagesCtrl
 * @description
 * # MessagesCtrl
 * Controller of the clientApp
 */
angular.module('clientApp')
  .controller('MessagesCtrl', function (Contact, $scope, Authentication, $location) {
    if(Authentication.isConnected()){
	    Contact.messages()
	    	.success(function(data){
	    		$scope.messages=data;
	    	});

	    $scope.remove=function(id){
	    	
	    }
    }else{
    	$location.path('/login');
    }
  });
