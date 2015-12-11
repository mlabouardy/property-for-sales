'use strict';

describe('Service: Upload', function () {

  // load the service's module
  beforeEach(module('clientApp'));

  // instantiate service
  var Upload;
  beforeEach(inject(function (_Upload_) {
    Upload = _Upload_;
  }));

  it('should do something', function () {
    expect(!!Upload).toBe(true);
  });

});
