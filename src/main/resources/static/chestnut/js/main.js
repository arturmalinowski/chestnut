angular
    .module('chestnutApp',  ['ngRoute'])
    .config(function($routeProvider, $locationProvider) {
                $locationProvider.html5Mode({
                             enabled: true,
                             requireBase: false
                });
    		    $routeProvider
    		    .when('/', {
    				    templateUrl: 'chestnut/index.html',
    				    controller: 'ChestnutCtrl'
    			})
    			.when('/:userId', {
                    	templateUrl: 'chestnut/index.html',
                    	controller: 'ChestnutCtrl'
                })
    			.otherwise({
                         redirectTo: 'chestnut/index.html'
                });
    })
    .controller('ChestnutCtrl', function ($scope, $http, $location, $window) {
        $scope.userId = $location.path();
        $scope.items = [];
        $scope.newItem = "";

        $http.get('items/user/'+$scope.userId).
           success(function(data, status, headers, config) {
             $scope.items = data;
        })

        $scope.addItem = function() {
            console.log($scope.newItem);
        }
    });
