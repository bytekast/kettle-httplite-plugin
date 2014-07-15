var app = angular.module('myApp', ['ui.bootstrap', 'ngGrid']);

app.controller('TabsDemoCtrl', function($scope, $location) {

  $scope.getUrlParams = function () {
    var vars = [], hash;
    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
    for(var i = 0; i < hashes.length; i++){
      hash = hashes[i].split('=');
      vars.push(hash[0]);
      vars[hash[0]] = hash[1];
    }
    return vars;
  }

  $scope.id = $scope.getUrlParams().id;

  MyStepRemoteProxy.getModel($scope.id, function(data){
    $scope.stepModel = data;
    $scope.$apply();
  });

  $scope.$watch("stepModel.stepName", function(newValue, oldValue) {
    MyStepRemoteProxy.applyModel($scope.id, $scope.stepModel);
  });


  $scope.tabs = [
    { title:'Dynamic Title 1', content:'Dynamic content 1' },
    { title:'Dynamic Title 2', content:'Dynamic content 2', disabled: true }
  ];

  $scope.alertMe = function() {
    setTimeout(function() {
      alert('You\'ve selected the alert tab!');
    });
  };
});

app.controller('GridDemoCtrl', function($scope) {
  $scope.myData = [{name: "Moroni", age: 50},
                   {name: "Tiancum", age: 43},
                   {name: "Jacob", age: 27},
                   {name: "Nephi", age: 29},
                   {name: "Enos", age: 34}];
  $scope.gridOptions = { 
      data: 'myData',
      enableCellSelection: true,
      enableRowSelection: false,
      enableCellEdit: true,
      columnDefs: [{field: 'name', displayName: 'Name', enableCellEdit: true}, 
                   {field:'age', displayName:'Age', enableCellEdit: true}]
  };
});
