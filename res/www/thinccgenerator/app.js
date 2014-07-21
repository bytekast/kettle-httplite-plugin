var app = angular.module('ccgenerator', ['ui.bootstrap', 'ngGrid']);

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

  ThinCCNumberGeneratorProxy.getModel($scope.id, function(data){
    $scope.stepModel = data;
    $scope.cardEntries = $scope.stepModel.cardEntries;

    $scope.$apply();
  });

  ThinCCNumberGeneratorProxy.getCardTypes(function(data){
    $scope.cardTypes = data;
    $scope.selectedCardType = $scope.cardTypes[0];

    $scope.$apply();
  });

  $scope.help = function() {
    ThinCCNumberGeneratorProxy.help($scope.id);
  };

  $scope.submit = function() {
    $scope.stepModel.cardEntries = $scope.cardEntries; // add card entries to model before submit
    ThinCCNumberGeneratorProxy.applyModel($scope.id, $scope.stepModel);
  };

  $scope.cancel = function() {
    ThinCCNumberGeneratorProxy.cancel($scope.id);
  };

  $scope.reset = function(){
    // defaults
    $scope.cardLength = 0;
    $scope.cardCount = 0;
  }

  $scope.addCardEntry = function(){

    if($scope.cardEntries == null){
      $scope.cardEntries = [];
    }

    $scope.cardEntries.push({
      type: $scope.selectedCardType.name,
      length: $scope.cardLength,
      count: $scope.cardCount
    });

    $scope.reset();
  }

  $scope.removeCardEntry = function(index){
    $scope.cardEntries.splice(index, 1);
  }

  // init
  $scope.reset();

});
