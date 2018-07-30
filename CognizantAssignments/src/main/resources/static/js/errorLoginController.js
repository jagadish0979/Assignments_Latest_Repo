//ErrorLoginController.js

angular
.module('app').controller('ErrorLoginController', function ($scope, $rootScope, errorDataFactory) {
	$scope.data = errorDataFactory.get();
	$rootScope.normalHeader = false;
	$rootScope.errorHeader = true;
});