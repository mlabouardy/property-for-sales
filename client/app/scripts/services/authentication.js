'use strict';

/**
 * @ngdoc service
 * @name clientApp.authentication
 * @description
 * # authentication
 * Service in the clientApp.
 */
angular.module('clientApp')
  .service('Authentication', function ($cookieStore, $http, $location) {
  	var connected=false;
    var role;

  	return{
  		login:function(){
  			var authdata=$cookieStore.get('authdata');
	    	$http.defaults.headers.common['Authorization'] = 'Basic ' + authdata; 
	    	return $http.get(SERVER_URL+'api/login');
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
  		},
      getRole:function(){
        return role;
      },
      redirect:function(){
        if(role=='ROLE_USER')
          $location.path('/dashboard');
        else
          $location.path('/admin');
      }
  	}
  });
