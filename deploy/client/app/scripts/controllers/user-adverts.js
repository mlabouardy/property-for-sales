'use strict';

/**
 * @ngdoc function
 * @name clientApp.controller:UserAdvertsCtrl
 * @description
 * # UserAdvertsCtrl
 * Controller of the clientApp
 */
angular.module('clientApp')
  .controller('UserAdvertsCtrl', function (Authentication, $location, Adverts, $scope, $routeParams) {
  	Authentication.isConnected()
      .success(function(role){
        if(role.name=='ROLE_ADMIN'){
          Adverts.getAdvertsByUserId($routeParams.id).success(function(adverts){
            $scope.adverts=adverts;
          });

          $scope.remove=function(id){
            Adverts.removeUserAdvert($routeParams.id, id)
              .success(function(){
                $scope.msg="Success";
                Adverts.getAdvertsByUserId($routeParams.id).success(function(adverts){
                  $scope.adverts=adverts;
                });
              })
              .error(function(){
                $scope.msg="Error";
              });
          }
        }else{
          $location.path('/login');
        }
      })
      .error(function(){
        $location.path('/login');
      });
  });
