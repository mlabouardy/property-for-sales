'use strict';

/**
 * @ngdoc service
 * @name clientApp.Upload
 * @description
 * # Upload
 * Service in the clientApp.
 */
angular.module('clientApp')
  .service('Upload', function ($http) {

  	return{
  		upload:function(file){
  			var fd = new FormData();
  			fd.append('file', file);
		    return $http.post(CLOUD_URL, fd, {
		        transformRequest: angular.identity,
		        headers: {
		          'Content-Type': undefined
		        }
		     });
  		}
  	}
  });
