'use strict';

describe('Controller: AdvertNotExistCtrl', function () {

  // load the controller's module
  beforeEach(module('clientApp'));

  var AdvertNotExistCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    AdvertNotExistCtrl = $controller('AdvertNotExistCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(AdvertNotExistCtrl.awesomeThings.length).toBe(3);
  });
});
