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
    var baseUrl='http://localhost:8080/property-for-sales/user/create';

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
    		console.log(JSON.stringify(data));
    		return $http.post(baseUrl,data);
    	}
    }
  });
