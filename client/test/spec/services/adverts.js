'use strict';

describe('Service: Adverts', function () {

  // load the service's module
  beforeEach(module('clientApp'));

  // instantiate service
  var Adverts;
  beforeEach(inject(function (_Adverts_) {
    Adverts = _Adverts_;
  }));

  it('should do something', function () {
    expect(!!Adverts).toBe(true);
  });

});
