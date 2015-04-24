angular
    .module('chestnutApp', [])
    .controller('ChestnutCtrl', function ($scope, $http) {
        $scope.userId = 'jonathansharifi';
        $scope.items = [];

        $http.get('items/user/'+$scope.userId).
           success(function(data, status, headers, config) {
             $scope.items = data;
        })
    });
