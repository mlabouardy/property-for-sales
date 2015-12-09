'use strict';

describe('Controller: AdViewCtrl', function () {

  // load the controller's module
  beforeEach(module('clientApp'));

  var AdViewCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    AdViewCtrl = $controller('AdViewCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(AdViewCtrl.awesomeThings.length).toBe(3);
  });
});
