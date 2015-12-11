'use strict';

/**
 * @ngdoc function
 * @name clientApp.controller:DashboardCtrl
 * @description
 * # DashboardCtrl
 * Controller of the clientApp
 */
 angular.module('clientApp')
 .controller('DashboardCtrl', function (Authentication, $location, $scope, $http, Adverts, Upload) {
   /* if(Authentication.isConnected() && Authentication.getRole()=='ROLE_USER'){
    	
    }else{
    	$location.path('/login');
    }*/

    Adverts.getAdverts().success(function(data){
    	$scope.adverts=data;
    });

    var MAX_FILES=5;
    var SIZE_LIMIT=500000; //500Ko
    var EXTENSIONS_ALLOW=['png','jpg','jpeg'];

    $scope.files=[];

    $scope.upload= function(img) {
    	var fileName=img.name;
    	var extension=fileName.split('.').pop();
    	var size=img.size;

    	if(size>SIZE_LIMIT){
    		$scope.file_upload_error="Maximum size is 500 Ko";
    	}else{
    		if(EXTENSIONS_ALLOW.indexOf(extension) == -1){
 				$scope.file_upload_error="File allowed: png, jpg and jpeg";
    		}else if($scope.files.length>=MAX_FILES){
    			$scope.file_upload_error="You can upload only 5 pictures";
    		}else{
    			Upload.upload(img).success(function(data){
		    		console.log(data);
		    		if(data.success){
		    			var file={
		    				url:data.url
		    			};
		    			$scope.files.push(file);
		    			$scope.file_upload_error=null;
		    		}
		    	});
    		}
    	}
    }
});
