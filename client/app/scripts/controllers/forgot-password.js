'use strict';

/**
 * @ngdoc function
 * @name clientApp.controller:ForgotPasswordCtrl
 * @description
 * # ForgotPasswordCtrl
 * Controller of the clientApp
 */
angular.module('clientApp')
  .controller('ForgotPasswordCtrl', function (User, $scope) {
    $scope.fogotpassword=function(){
    	User.forgotPassword($scope.email)
    	.success(function(msg){
    		toastr.success(msg, 'Property for sales');
    	})
    	.error(function(msg){
    		toastr.warning(msg, 'Property for sales');
    	});
    }
    
  });
