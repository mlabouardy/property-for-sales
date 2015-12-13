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
  	return{
  		getAdverts:function(){
  			return $http.get(SERVER_URL+'adverts');
  		},
  		findById:function(id){
  			return $http.get(SERVER_URL+'adverts/'+id);
  		},
      getAdvertsByUserId:function(user_id){
        return $http.get(SERVER_URL+'user/'+user_id+'/adverts');
      },
      createAdvert:function(data){
        return $http.post(SERVER_URL+'api/adverts/create',data);
      },
      getUserAdverts:function(){
        return $http.get(SERVER_URL+'api/user/adverts');
      },
      removeAdvert:function(id){
        return $http.get(SERVER_URL+'api/user/adverts/'+id+'/delete');
      },
      removeUserAdvert:function(id_user, id_advert){
        return $http.get(SERVER_URL+'api/user/'+id_user+'/adverts/'+id_advert+'/delete');
      }
  	};
  });
