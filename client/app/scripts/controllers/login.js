'use strict';

/**
 * @ngdoc function
 * @name clientApp.controller:LoginCtrl
 * @description
 * # LoginCtrl
 * Controller of the clientApp
 */
 angular.module('clientApp')
 .controller('LoginCtrl', function ($scope, $base64, Authentication, $location, $cookieStore) {

 	if(!Authentication.isConnected()){
 		$scope.login = function(){
 			var authdata = $base64.encode($scope.username + ':' + $scope.password);
 			$cookieStore.put('authdata',authdata);
 			Authentication.login()
 			.success(function(data){
 				Authentication.connectionSuccess(data.name);
 				Authentication.redirect();
 			})
 			.error(function(data, status, headers, config){
 				$scope.error="Error";
 			});
 		};
 	}else{
 		Authentication.redirect();
 	}
 });
