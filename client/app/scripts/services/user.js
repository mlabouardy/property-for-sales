'use strict';

/**
 * @ngdoc service
 * @name clientApp.User
 * @description
 * # User
 * Service in the clientApp.
 */
angular.module('clientApp')
  .service('User', function ($http) {

    return{
    	register:function(data){
    		return $http.post(SERVER_URL+'user/create',data);
    	},
        getUsers:function(){
            return $http.get(SERVER_URL+'api/users');
        },
        getProfile:function(){
            return $http.get(SERVER_URL+'api/profile');
        },
        updateProfile:function(data){
            return $http.post(SERVER_URL+'api/profile/update',data);
        },
        delete:function(id){
            return $http.get(SERVER_URL+'api/user/'+id+'/delete');
        },
        changePicture:function(data){
            return $http.post(SERVER_URL+'api/profile/picture/update',data);
        },
        updateCriteria:function(data){
            return $http.post(SERVER_URL+'api/criteria/update',data);
        },
        getCriteria:function(){
            return $http.get(SERVER_URL+'api/criteria');
        }
    }
  });
