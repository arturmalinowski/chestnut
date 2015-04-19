angular
    .module('chestnutApp', [])
    .controller('ChestnutCtrl', function ($scope, $http) {

    $scope.items = [];

    $http.get('items').
       success(function(data, status, headers, config) {
         $scope.items = data;
    })

    });
