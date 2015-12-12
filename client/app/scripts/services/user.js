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
    var baseUrl='http://localhost:8080/property-for-sales/';

    return{
    	register:function(fname, lname, em, pass){
    		var data={
    			firstName: fname,
    			lastName: lname,
    			email: em,
    			password: pass,
    			address: "",
    			phone: ""
    		};
    		return $http.post(baseUrl+'user/create',data);
    	},
        getUsers:function(){
            return $http.get(baseUrl+'api/users');
        },
        getProfile:function(){
            return $http.get(baseUrl+'api/profile');
        },
        updateProfile:function(data){
            return $http.post(baseUrl+'api/profile/update',data);
        },
        delete:function(id){
            return $http.get(baseUrl+'api/user/'+id+'/delete');
        }
    }
  });
