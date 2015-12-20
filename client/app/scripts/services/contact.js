'use strict';

/**
 * @ngdoc service
 * @name clientApp.Contact
 * @description
 * # Contact
 * Service in the clientApp.
 */
angular.module('clientApp')
  .service('Contact', function ($http) {
    return{
  		send:function(data){
  			return $http.post(SERVER_URL+'api/user/advert/contact',data);
  		},
  		messages:function(){
  			return $http.get(SERVER_URL+'api/user/messages');
  		}
  	}
  });
