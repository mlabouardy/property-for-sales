'use strict';

describe('Controller: FavoritesCtrl', function () {

  // load the controller's module
  beforeEach(module('clientApp'));

  var FavoritesCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    FavoritesCtrl = $controller('FavoritesCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(FavoritesCtrl.awesomeThings.length).toBe(3);
  });
});
