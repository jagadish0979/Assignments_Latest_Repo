angular
.module('app').controller('AssignmentFEResultController', function ($scope, $location, $window, assignmentFEDataFactory) {
	$scope.data = assignmentFEDataFactory.get();
});