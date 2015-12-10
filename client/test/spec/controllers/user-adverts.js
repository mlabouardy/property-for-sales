'use strict';

describe('Controller: UserAdvertsCtrl', function () {

  // load the controller's module
  beforeEach(module('clientApp'));

  var UserAdvertsCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    UserAdvertsCtrl = $controller('UserAdvertsCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(UserAdvertsCtrl.awesomeThings.length).toBe(3);
  });
});
