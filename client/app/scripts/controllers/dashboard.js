'use strict';

/**
 * @ngdoc function
 * @name clientApp.controller:DashboardCtrl
 * @description
 * # DashboardCtrl
 * Controller of the clientApp
 */
angular.module('clientApp')
  .controller('DashboardCtrl', function (Authentication, $location, User) {
    if(!Authentication.isConnected()){
    	$location.path('/login');
    }else{
    	console.log('hello');
    	User.getUsers().success(function(data){
    		console.log(data);
    	});
    }
  });
