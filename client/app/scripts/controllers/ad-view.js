'use strict';

/**
 * @ngdoc function
 * @name clientApp.controller:AdViewCtrl
 * @description
 * # AdViewCtrl
 * Controller of the clientApp
 */
 angular.module('clientApp')
 .controller('AdViewCtrl', function ($routeParams, $scope, Adverts, $http, $location) {
 	$scope.pageUrl=$location.absUrl();
 	Adverts.findById($routeParams.id).success(function(data){
 		$scope.myInterval = 3000;
  		$scope.noWrapSlides = false;
 		$scope.advert=data;
 		var location=$scope.advert.location;
 		$http.get(GOOGLE_MAP+location)
 		.success(function(data){
 			var lat=data.results[0].geometry.location.lat;
 			var lng=data.results[0].geometry.location.lng;

 			google.maps.event.addDomListener(window, 'load', initialize(lat, lng));
 			
 		})
 		.error(function(){

 		});
 	});

 	function initialize(lat, lng) {
 		var latlng = new google.maps.LatLng(lat,lng);

 		var mapOptions = {
 			center: latlng,
 			scrollWheel: false,
 			zoom: 13
 		};
 		
 		var marker = new google.maps.Marker({
 			position: latlng,
 			url: '/',
 			animation: google.maps.Animation.DROP
 		});
 		
 		var map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);
 		marker.setMap(map);

 	};

 	

 });
