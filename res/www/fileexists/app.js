var app = angular.module('fileexists', ['ui.bootstrap', 'ngGrid']);

app.controller('formController', function($scope, $location) {

  $scope.getUrlParams = function () {
    var vars = [], hash;
    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
    for(var i = 0; i < hashes.length; i++){
      hash = hashes[i].split('=');
      vars.push(hash[0]);
      vars[hash[0]] = hash[1];
    }
    return vars;
  };

  $scope.id = $scope.getUrlParams().id;

  FileExistsRemoteProxy.getModel($scope.id, function(data){
    $scope.stepModel = data;
    $scope.$apply();
  });

  $scope.help = function() {
    FileExistsRemoteProxy.help($scope.id);
  };

  $scope.submit = function() {
    //FileExistsRemoteProxy.applyModel($scope.id, $scope.stepModel);
    FileExistsRemoteProxy.submit($scope.id, $scope.stepModel);
  };

  $scope.cancel = function() {
    FileExistsRemoteProxy.cancel($scope.id);
  };

});
