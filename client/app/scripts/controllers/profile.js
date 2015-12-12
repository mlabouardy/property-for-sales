'use strict';

/**
 * @ngdoc function
 * @name clientApp.controller:ProfileCtrl
 * @description
 * # ProfileCtrl
 * Controller of the clientApp
 */
angular.module('clientApp')
  .controller('ProfileCtrl', function (Authentication, $location, $scope, User, $cookieStore, $base64, $http) {
	   if(Authentication.isConnected()){
	    	User.getProfile().success(function(profile){
	    		$scope.profile=profile;
	    	});

	    	$scope.password=false;

	    	$scope.update=function(){
    			User.updateProfile($scope.profile)
    				.success(function(data){
    					$scope.msg="Success";
			    		var authdata = $base64.encode($scope.profile.email + ':' + $scope.profile.password);
		 				$cookieStore.put('authdata',authdata);
		 				$http.defaults.headers.common['Authorization'] = 'Basic ' + authdata; 
		 				$scope.password=false;
    				})
    				.error(function(){
    					$scope.msg="Error";
    				})
	    	}

	    	$scope.password_changed=function(){
	    		$scope.password=true;
	    	}
	    }else{
	    	$location.path('/login');
	    }
  });
