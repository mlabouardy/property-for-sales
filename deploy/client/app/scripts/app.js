'use strict';

/**
 * @ngdoc overview
 * @name clientApp
 * @description
 * # clientApp
 *
 * Main module of the application.
 */
angular
  .module('clientApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
    'base64',
    'angularUtils.directives.dirPagination',
    'ui.bootstrap'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl',
        title: 'Home'
      })
      .when('/register', {
        templateUrl: 'views/register.html',
        controller: 'RegisterCtrl',
        title: 'Sign up'
      })
      .when('/login', {
        templateUrl: 'views/login.html',
        controller: 'LoginCtrl',
        title: 'Login'
      })
      .when('/search', {
        templateUrl: 'views/search.html',
        controller: 'SearchCtrl',
        title: 'Search Advertisments'
      })
      .when('/dashboard', {
        templateUrl: 'views/dashboard.html',
        controller: 'DashboardCtrl',
        title: 'Dashboard'
      })
      .when('/ad-view/:id', {
        templateUrl: 'views/ad-view.html',
        controller: 'AdViewCtrl',
        title: 'Advertisment details'
      })
      .when('/logout', {
        templateUrl: 'views/logout.html',
        controller: 'LogoutCtrl'
      })
      .when('/profile', {
        templateUrl: 'views/profile.html',
        controller: 'ProfileCtrl',
        title: 'Profile'
      })
      .when('/user/:id/adverts', {
        templateUrl: 'views/user-adverts.html',
        controller: 'UserAdvertsCtrl',
        title: 'User Advertisment'
      })
      .when('/admin', {
        templateUrl: 'views/admin.html',
        controller: 'AdminCtrl',
        title: 'Admin Board'
      })
      .when('/contact/:id', {
        templateUrl: 'views/contact.html',
        controller: 'ContactCtrl',
        title:'Contact'
      })
      .when('/advert-not-exist', {
        templateUrl: 'views/advert-not-exist.html',
        controller: 'AdvertNotExistCtrl'
      })
      .when('/messages', {
        templateUrl: 'views/messages.html',
        controller: 'MessagesCtrl',
        title: 'Mailbox'
      })
      .when('/favorites', {
        templateUrl: 'views/favorites.html',
        controller: 'FavoritesCtrl',
        title: 'List of favorites'
      })
      .when('/forgot-password', {
        templateUrl: 'views/forgot-password.html',
        controller: 'ForgotPasswordCtrl',
        title: 'Forgot password'
      })
      .when('/test', {
        templateUrl: 'views/test.html',
        controller: 'TestCtrl',
        controllerAs: 'test'
      })
      .otherwise({
        redirectTo: '/'
      });
  })
  .run(function ($rootScope, $route, Authentication, User) {
        $rootScope.$on("$routeChangeSuccess", function () {
            document.title = $route.current.title;
            Authentication.isConnected()
              .success(function(){
                $rootScope.isConnected=true;
              })
              .error(function(){
                $rootScope.isConnected=false;
              });

              User.countMessages()
                .success(function(data){
                  $rootScope.countMessages=data;
                });
            $rootScope.isActive = function (path) {
              return $location.path() === path;
            }
        });
    })
 .directive('fileModel', ['$parse', function($parse) {
  return {
    restrict: 'A',
    link: function(scope, element, attrs) {
      var model = $parse(attrs.fileModel);
      var modelSetter = model.assign;

      element.bind('change', function() {
        scope.$apply(function() {
          modelSetter(scope, element[0].files[0]);
        });
      });
    }
  };
}])
