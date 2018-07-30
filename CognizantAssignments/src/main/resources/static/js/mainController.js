//mainController.js
angular
.module('app').controller('MainController', function ($rootScope,$location) {
	$rootScope.normalHeader = true;
	$rootScope.errorHeader = false; 	
	$rootScope.mainHeader.show = true;
});