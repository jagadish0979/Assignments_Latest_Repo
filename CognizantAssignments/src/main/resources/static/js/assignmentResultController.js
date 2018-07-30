angular
.module('app').controller('AssignmentResultController', function ($scope, $location, $window, assignmentDataFactory) {
	$scope.data = assignmentDataFactory.get();
});