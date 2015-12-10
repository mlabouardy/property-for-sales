'use strict';

/**
 * @ngdoc service
 * @name clientApp.Adverts
 * @description
 * # Adverts
 * Service in the clientApp.
 */
angular.module('clientApp')
  .service('Adverts', function ($http) {
    var baseUrl='http://localhost:8080/property-for-sales/';
  	return{
  		getAdverts:function(){
  			return $http.get(baseUrl+'adverts');
  		},
  		findById:function(id){
  			return $http.get(baseUrl+'adverts/'+id);
  		},
      getAdvertsByUserId:function(user_id){
        return $http.get(baseUrl+'user/'+user_id+'/adverts');
      } 
  	};
  });
