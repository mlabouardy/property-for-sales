'use strict';

/**
 * @ngdoc function
 * @name clientApp.controller:AdminCtrl
 * @description
 * # AdminCtrl
 * Controller of the clientApp
 */
angular.module('clientApp')
  .controller('AdminCtrl', function (Authentication, $location, $scope, User) {
    Authentication.isConnected()
        .success(function(role){
            if(role.name=='ROLE_ADMIN'){
                toastr.info('Welcome to property for sales !');

        User.getUsers().success(function(data){
            $scope.users=data;
            if(data.length==0){
                toastr.success('No adverts found yet!', 'Property for sales');
            }
        });

        $scope.refresh=function(){
            User.getUsers().success(function(data){
                $scope.users=data;
                if(data.length==0){
                    toastr.success('No adverts found yet!', 'Property for sales');
                }
            });
        }

        $scope.delete=function(id){
            User.delete(id)
            .success(function(){
                $scope.msg="Success";
                $scope.refresh();
            })
            .error(function(){
                $scope.msg="Error";
            })
        }
            }else{
                $location.path('/login');
            }
        })
        .error(function(){
            $location.path('/login');
        });

  });
