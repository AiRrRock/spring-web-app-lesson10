angular.module('market-front').controller('orderConfirmationController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8189/market/';

    $scope.loadCart = function () {
        $http({
            url: 'http://localhost:8191/market-cart/api/v1/cart/' + $localStorage.webMarketGuestCartId,
            method: 'GET'
        }).then(function (response) {
            $scope.cart = response.data;
        });
    };

    $scope.createOrder = function () {
        $scope.orderDetails.userName = $localStorage.webMarketUser.username;
        $scope.orderDetails.cartUid = $localStorage.webMarketGuestCartId;
        $http({
            url: 'http://localhost:8192/market-order/api/v1/orders/',
            method: 'POST',
            data: $scope.orderDetails
        }).then(function (response) {
            alert('Ваш заказ успешно сформирован');
            $location.path('/');
        });
    };

    $scope.loadCart();
});