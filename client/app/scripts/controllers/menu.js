

angular.module('clientApp')
  .controller('MenuCtrl', function ($scope, $location, Authentication) {
    $scope.isActive = function (path) {
       	return $location.path() === path;
    }

    $scope.isConnected=function(){
    	return Authentication.isConnected();
    }
  });

