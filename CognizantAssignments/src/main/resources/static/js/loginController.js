//loginController.js
angular
.module('app').controller('LoginController', function ($scope, $rootScope, $location, $http , errorDataFactory, CONSTANTS) {
	var vm = this;
	$rootScope.mainHeader = {};
	$rootScope.normalHeader = true;
	$rootScope.errorHeader = false;
	$rootScope.mainHeader.show = false;
    var errorObject = {
    		timestamp: "",
    		message: "",
    		details: ""
    	    };    
    	
	    $scope.doLogin = function () {
	        if ($scope.loginForm.$valid) {  
            var dataObject = { userName : vm.userName, password : Array.from(vm.password)};
            $http.post(CONSTANTS.loginURL,dataObject).then(
            		function (response) {
            			var data = response.data;
            			$rootScope.normalHeader = true;
            			$rootScope.errorHeader = false;            			
                        window.sessionStorage["userDetails"] = JSON.stringify(data);
                        $rootScope.userDetails = JSON.parse(window.sessionStorage["userDetails"]);
            			$location.path(data.url);
		            },
		            function (error) {
		            	console.log("errorDetailsJSONString [ " + JSON.stringify(error.data) + " ]")
		            	var data = error.data;
		            	errorObject = {
		                		timestamp: data.timestamp,
		                		message: data.message,
		                		details: data.details
		                	    };   
		            	errorDataFactory.set(errorObject);
        			    $location.path("/error_login");
		            }	
	            )
	        } 
	    }	
});