'use strict';

/**
 * @ngdoc function
 * @name clientApp.controller:ProfileCtrl
 * @description
 * # ProfileCtrl
 * Controller of the clientApp
 */
angular.module('clientApp')
  .controller('ProfileCtrl', function (Authentication, $location, $scope, User, Upload, $cookieStore, $base64, $http) {
	   Authentication.isConnected()
	   	.success(function(){
	   		User.getProfile().success(function(profile){
	    		$scope.profile=profile;
	    	});

	    	$(":file").filestyle({input: false, buttonText: " Choose picture", badge: false});
		    document.getElementById("files").onchange = function () {
			    var reader = new FileReader();

			    reader.onload = function (e) {
			        document.getElementById("profile").src = e.target.result;
			    };
			    reader.readAsDataURL(this.files[0]);
			};

		    var SIZE_LIMIT=500000; //500Ko
		    var EXTENSIONS_ALLOW=['png','jpg','jpeg'];
			$scope.upload=function(img){
				var fileName=img.name;
		    	var extension=fileName.split('.').pop();
		    	var size=img.size;

		    	if(size>SIZE_LIMIT){
		    		$scope.file_upload_error="Maximum size is 500 Ko";
		    	}else{
		    		if(EXTENSIONS_ALLOW.indexOf(extension) == -1){
		    			$scope.file_upload_error="File allowed: png, jpg and jpeg";
		    		}else{
		    			Upload.upload(img).success(function(data){
		    				if(data.success){
		    					//upload to server
		    					var data={
					                link: data.filename
					            };
		    					User.changePicture(data)
		    						.success(function(){
		    							toastr.success('Profile picture successfuly updated!', 'Property for sales');
		    						})
		    						.error(function(){
		    							toastr.error('Something went wrong', 'Property for sales');
		    						});
		    					$scope.file_upload_error=null;
		    				}
		    			});
		    		}
		    	}
			}

	    	$scope.password=false;

	    	$scope.update=function(){
    			User.updateProfile($scope.profile)
    				.success(function(data){
    					$scope.msg="Success";
			    		var authdata = $base64.encode($scope.profile.email + ':' + $scope.profile.password);
		 				$cookieStore.put('authdata',authdata);
		 				$http.defaults.headers.common['Authorization'] = 'Basic ' + authdata; 
		 				$scope.password=false;
    				})
    				.error(function(){
    					$scope.msg="Error";
    				})
	    	}

	    	$scope.password_changed=function(){
	    		$scope.password=true;
	    	}
	   	})
	   	.error(function(){
	   		$location.path('/login');
	   	});

	   
  });
