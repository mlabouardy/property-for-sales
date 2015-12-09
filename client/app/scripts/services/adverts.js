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
    var baseUrl='http://localhost:8080/property-for-sales/adverts';
  	return{
  		getAdverts:function(){
  			return $http.get(baseUrl);
  		},
  		findById:function(id){
  			return $http.get(baseUrl+'/'+id);
  		}
  	};
  });
