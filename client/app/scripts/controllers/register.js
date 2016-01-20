'use strict';

/**
 * @ngdoc function
 * @name clientApp.controller:RegisterCtrl
 * @description
 * # RegisterCtrl
 * Controller of the clientApp
 */
angular.module('clientApp')
  .controller('RegisterCtrl', function (Authentication, $location, $scope, User) {
    Authentication.isConnected()
        .success(function(){
            $location.path('/dashboard');
        })
        .error(function(){
            $scope.register=function(){
            var data={
                firstName: $scope.firstName,
                lastName: $scope.lastName,
                email: $scope.email,
                password: $scope.password,
                address: "",
                phone: ""
            };
            User.register(data)
                .success(function(data){
                    $location.path('/login');
                })
                .error(function(){
                    $scope.error="Error";
                });
       
        }
    });
  });
