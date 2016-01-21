'use strict';

/**
 * @ngdoc function
 * @name clientApp.controller:ContactCtrl
 * @description
 * # ContactCtrl
 * Controller of the clientApp
 */
angular.module('clientApp')
  .controller('ContactCtrl', function ($scope, Adverts, $routeParams, $location, Contact) {
    Adverts.findById($routeParams.id)
    	.success(function(data){
    		$scope.detail=data;

    		$scope.send=function(){
    			var data={
    				idReceiver:$scope.detail.owner.id,
    				idAdvert:$scope.detail.id,
    				nameSender:$scope.name,
    				emailSender:$scope.email,
    				phoneSender:$scope.phone,
    				msgSender:$scope.message
    			}
    			Contact.send(data)
    				.success(function(msg){
    					console.log(msg);
    					toastr.success(msg, 'Property for sales');
    					$scope.name="";
    					$scope.email="";
    					$scope.phone="";
    					$scope.message="";
    				})
    				.error(function(msg){
    					console.log(msg);
    					toastr.warning(msg, 'Property for sales');
    				});
    		}
    	})
    	.error(function(data){
    		$location.path('/advert-not-exist');
    	});
  });
