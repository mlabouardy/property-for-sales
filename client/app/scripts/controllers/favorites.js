'use strict';

/**
 * @ngdoc function
 * @name clientApp.controller:FavoritesCtrl
 * @description
 * # FavoritesCtrl
 * Controller of the clientApp
 */
angular.module('clientApp')
  .controller('FavoritesCtrl', function (User, $scope, $location, Authentication) {
     if(Authentication.isConnected()){
     		User.favorites()
	     	.success(function(data){
	     		$scope.favorites=data;
	     	});

	     	$scope.remove=function(id){
	     		User.removeFavorite(id)
	     			.success(function(msg){
	     				toastr.success(msg, 'Property for sales');	
	     					User.favorites()
						     	.success(function(data){
						     		$scope.favorites=data;
						     	});
	     			})
	     			.error(function(msg){
	     				toastr.warning(msg, 'Property for sales');
	     			});
	     	}
	 }else{
	     $location.path('/login');
	 }
     
  });
