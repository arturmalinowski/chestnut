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
    			.otherwise({
                         redirectTo: 'chestnut/index.html'
                });
    })
    .controller('ChestnutCtrl', function ($scope, $http, $location, $window) {
        $scope.items = [];
        $scope.newItem = "";

        $http.get('items').
           success(function(data, status, headers, config) {
             $scope.items = data;
        })

        $scope.addItem = function() {
            $http.post('items', $scope.newItem).
              success(function(data, status, headers, config) {
                   $.growl.notice({ title: data.title, message: "Added to Library" });
                   $http.get('items').
                    success(function(data, status, headers, config) {
                      $scope.items = data;
                   })
                   $('#titleId').val("");
                   $('#item-input').toggle();
                   $('.add-background').toggleClass('glow');
              });
        }
    });
