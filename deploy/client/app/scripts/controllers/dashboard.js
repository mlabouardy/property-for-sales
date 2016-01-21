'use strict';

/**
 * @ngdoc function
 * @name clientApp.controller:DashboardCtrl
 * @description
 * # DashboardCtrl
 * Controller of the clientApp
 */
 angular.module('clientApp')
 .controller('DashboardCtrl', function (Authentication, $location, $scope, $http, Adverts, Upload, User) {
 	Authentication.isConnected()
 	.success(function(role){
 		if(role.name=='ROLE_USER'){
 			toastr.info('Welcome to property for sales !');

 			$scope.cities=["Bordeaux", "Paris", "Nantes","Rennes","Toulouse","Nice","Lyon"];
 			$scope.types=["T1","Flat","T2","T4","Studio"];
 			
 			User.getCriteria()
 			.success(function(data){
 				$scope.keyword=data;
 			});

 			Adverts.getUserAdverts().success(function(data){
 				$scope.adverts=data;
 			});

 			$scope.refresh=function(){
 				Adverts.getUserAdverts().success(function(data){
 					$scope.adverts=data;
 				});
 			}

 			$scope.edit=function(){
 				toastr.info('Not implemented due to time');
 			}

 			$scope.remove=function(id){
 				Adverts.removeAdvert(id).success(function(data){
 					$scope.refresh();
 					toastr.success('Advert successfuly removed !', 'Property for sales');
 				});
 			}

 			$scope.updateCriteria=function(){
 				$scope.keyword.price=parseFloat($scope.keyword.price);
 				User.updateCriteria($scope.keyword)
 				.success(function(){
 					toastr.success('Search criteria successfuly updated!', 'Property for sales');
 				})
 				.error(function(){
 					toastr.warning('Something went wrong!', 'Property for sales');
 				});
 			}

 			var MAX_FILES=5;
		    var SIZE_LIMIT=500000; //500Ko
		    var EXTENSIONS_ALLOW=['png','jpg','jpeg'];

		    $scope.files=[];

		    $(":file").filestyle({input: false, buttonText: " Choose picture", badge: false});

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
		    				if(data.success){
		    					var file={
		    						link:data.filename
		    					};
		    					$scope.files.push(file);
		    					$scope.file_upload_error=null;
		    				}
		    			});
		    		}
		    	}
		    }

		    $scope.create=function(){
		    	if($scope.files.length==MAX_FILES){
		    		if($scope.description==undefined || $scope.type==undefined 
		    			|| $scope.price==undefined || $scope.surface==undefined 
		    			|| $scope.location==undefined){
		    			toastr.warning('All fields must be filed', 'Property for sales');
		    	}else{
		    		var data={
		    			description: $scope.description,
		    			type: $scope.type,
		    			price: parseFloat($scope.price),
		    			surface: $scope.surface,
		    			location: $scope.location,
		    			pictures: $scope.files
		    		};
		    		Adverts.createAdvert(data).success(function(data){
		    			toastr.success('Advert successfuly created!', 'Property for sales')
		    			$scope.refresh();
		    		});
		    		$scope.description="";
		    		$scope.type="";
		    		$scope.price="";
		    		$scope.location="";
		    		$scope.files=[];
		    		$scope.surface="";
		    	}
		    }else{
		    	toastr.warning('You need 5 pictures to create the advert!', 'Property for sales');
		    }
		};

	}else{
		$location.path('/login');
	}
})
.error(function(){
	$location.path('/login');
});
});
