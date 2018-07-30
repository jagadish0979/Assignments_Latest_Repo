//logoutController.js
angular
.module('app').controller('LogoutController', function ($rootScope,$route,$window) {
	var vm = this;
	$rootScope.normalHeader = false;
	$rootScope.errorHeader = false;
	$rootScope.mainHeader.show = false;
	$window.sessionStorage["userDetails"] = null;
	sessionStorage.removeItem("userDetails")
    $rootScope.userDetails = {};
	$window.location.href = '#!';
});