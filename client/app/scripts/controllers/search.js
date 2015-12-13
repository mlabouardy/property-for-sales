'use strict';

/**
 * @ngdoc function
 * @name clientApp.controller:SearchCtrl
 * @description
 * # SearchCtrl
 * Controller of the clientApp
 */
angular.module('clientApp')
  .controller('SearchCtrl', function ($scope, Adverts, Authentication, User) {
     Adverts.getAdverts().success(function(data){
     	$scope.adverts=data;
     });

     if(Authentication.isConnected() && Authentication.getRole()=='ROLE_USER'){
     	User.getCriteria()
     		.success(function(data){
     			$scope.keyword=data;
     		})
     		.error(function(){
     			toastr.warning('Something went wrong!', 'Property for sales');
     		});
     }
 
  });
