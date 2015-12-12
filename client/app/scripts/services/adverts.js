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
      },
      createAdvert:function(data){
        return $http.post(baseUrl+'api/adverts/create',data);
      },
      getUserAdverts:function(){
        return $http.get(baseUrl+'api/user/adverts');
      },
      removeAdvert:function(id){
        return $http.get(baseUrl+'api/user/adverts/'+id+'/delete');
      },
      removeUserAdvert:function(id_user, id_advert){
        return $http.get(baseUrl+'api/user/'+id_user+'/adverts/'+id_advert+'/delete');
      }
  	};
  });
