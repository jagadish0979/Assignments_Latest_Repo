//ErrorController.js

angular
.module('app').controller('ErrorController', function ($scope, errorDataFactory) {
	$scope.data = errorDataFactory.get();
});