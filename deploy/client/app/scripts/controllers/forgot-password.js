'use strict';

/**
 * @ngdoc function
 * @name clientApp.controller:ForgotPasswordCtrl
 * @description
 * # ForgotPasswordCtrl
 * Controller of the clientApp
 */
angular.module('clientApp')
  .controller('ForgotPasswordCtrl', function ($location, User, $scope, Authentication) {
    Authentication.isConnected()
        .success(function(){
            $scope.fogotpassword=function(){
                    User.forgotPassword($scope.email)
                    .success(function(msg){
                        toastr.success(msg, 'Property for sales');
                    })
                    .error(function(msg){
                        toastr.warning(msg, 'Property for sales');
                    });
                }
        })
        .error(function(){
            $location.path('/dashboard');
        });
    
    
  });
