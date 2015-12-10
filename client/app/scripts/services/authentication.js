'use strict';

/**
 * @ngdoc service
 * @name clientApp.authentication
 * @description
 * # authentication
 * Service in the clientApp.
 */
angular.module('clientApp')
  .service('Authentication', function ($cookieStore, $http) {

  	var baseUrl='http://localhost:8080/property-for-sales/api/login';
  	var connected=false;
    var role;

  	return{
  		login:function(){
  			var authdata=$cookieStore.get('authdata');
	    	$http.defaults.headers.common['Authorization'] = 'Basic ' + authdata; 
	    	return $http.get(baseUrl);
  		},
  		logout:function(){
  			$cookieStore.remove('authdata');
	    	$http.defaults.headers.common['Authorization'] = 'Basic'; 
	    	connected=false;
  		},
  		connectionSuccess:function(type){
  			connected=true;
        role=type;
  		},
  		isConnected:function(){
  			return connected;
  		}
  	}
  });
