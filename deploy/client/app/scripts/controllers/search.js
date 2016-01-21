'use strict';

/**
 * @ngdoc function
 * @name clientApp.controller:SearchCtrl
 * @description
 * # SearchCtrl
 * Controller of the clientApp
 */
angular.module('clientApp')
  .controller('SearchCtrl', function ($scope, Adverts, Authentication, User, $location) {
     Adverts.getAdverts().success(function(data){
     	$scope.adverts=data;
     });

     Authentication.isConnected()
        .success(function(){
            if(Authentication.getRole=='ROLE_USER'){
                User.getCriteria()
                    .success(function(data){
                        $scope.keyword=data;
                    })
                    .error(function(){
                        toastr.warning('Something went wrong!', 'Property for sales');
                    });
            }
        });

    $scope.addFavorite=function(id){
        Authentication.isConnected()
            .success(function(){
                User.addFavorite(id)
                .success(function(msg){
                    toastr.success(msg, 'Property for sales');
                })
                .error(function(msg){
                    toastr.warning(msg, 'Property for sales');
                });
            })
            .error(function(){
                $location.path('/login');
            });
    }
  });
