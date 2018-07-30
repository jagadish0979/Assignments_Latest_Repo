//app.js
var app = angular.module("app", ["ngRoute"]);
app.config(function($routeProvider) {
$routeProvider
.when("/", {
	controller: 'LoginController',
    templateUrl : "/assignments/views/login.html",
	controllerAs: 'vm'
})
.when("/main", {
	controller: 'MainController',
    templateUrl : "/assignments/views/main.html",
	controllerAs: 'vm'
})
.when("/assignments", {
	controller: 'AssignmentsController',
    templateUrl : "/assignments/views/assignments.html",
	controllerAs: 'vm'
})
.when("/assignments-fe", {
	controller: 'AssignmentFEController',
	templateUrl: '/assignments/views/assignments-fe.html',
	controllerAs: 'vm'
}) 
.when("/assignments-fe-result", {
	controller: 'AssignmentFEResultController',
	templateUrl: '/assignments/views/assignments-fe-result.html',
	controllerAs: 'vm'
}) 
.when("/assignments-result", {
	controller: 'AssignmentResultController',
	templateUrl: '/assignments/views/assignments-result.html',
	controllerAs: 'vm'
})
.when("/error", {
	controller: 'ErrorController',
    templateUrl : "/assignments/views/error.html",
	controllerAs: 'vm'
})
.when("/error_login", {
	controller: 'ErrorLoginController',
    templateUrl : "/assignments/views/error_login.html",
	controllerAs: 'vm'
})
.otherwise({ redirectTo: '/' });
});

app.constant("CONSTANTS", {
	     loginURL: "/assignments/doLogin",
	processFileURL: "/assignments/processFile",
              CSV: "csv",
              XML: "xml"
});

app.config(function ($provide) {

    $provide.decorator('$exceptionHandler', function ($delegate) {

        return function (exception, cause) {
            $delegate(exception, cause);
        	errorObject = {
            		message: "Unexpected Error Occured. Please Contat System Admin!"
            	    }; 
            errorDataFactory.set(data);
		    $window.location.href = '/assignments/#!/error.html';        };
    });
});
