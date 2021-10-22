angular.module('market-front').controller('profileController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/market-core/';

    $scope.loadOrders = function () {
        $http({
            url: 'http://localhost:8192/market-order/api/v1/orders/' + $localStorage.webMarketUser.username,
            method: 'GET'
        }).then(function (response) {
            $scope.orders = response.data;
        });
    };

    $scope.loadMyProfile = function () {
        $http({
            url: contextPath + 'api/v1/users/me',
            method: 'GET'
        }).then(function (response) {
            $scope.userProfile = response.data;
        });
    };

    $scope.loadOrders();
    $scope.loadMyProfile();
});