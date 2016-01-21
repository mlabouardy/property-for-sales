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
    Authentication.isConnected()
    	.success(function(){
    		Contact.messages()
	    	.success(function(data){
	    		$scope.messages=data;
	    	});

	    $scope.remove=function(id){
	    	console.log(id);
	    	Contact.delete(id)
	    		.success(function(msg){
	    			toastr.success(msg, 'Property for sales');
	    			Contact.messages()
				    	.success(function(data){
				    		$scope.messages=data;
				    	});
	    		})
	    		.error(function(msg){
	    			toastr.warning(msg, 'Property for sales');
	    		});
	    }
    	})
    	.error(function(){
    		$location.path('/login');
    	});
  });
