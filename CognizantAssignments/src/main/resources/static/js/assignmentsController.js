//AssignmentsController.js

angular
.module('app')
.controller(
		'AssignmentsController',
		function($scope, $location, $window, $http,
				assignmentDataFactory, errorDataFactory, CONSTANTS) {
			var vm = this;
			$scope.errorMessage = false;
			
			var uploadUrl = "";
			var obj = {
					transactionReference : "",
					description : "",
					failedReason : ""
			};
			var errorObject = {
					timestamp : "",
					message : "",
					details : ""
			};

			$scope.processFile = function() {
				var file = document.getElementById("assignmentFile").files[0];
				if(file){
					var formData = new FormData();
					formData.append('file', file);

					$http.post(CONSTANTS.processFileURL,formData, {
								transformRequest : angular.identity,
								headers : {
									'Content-Type' : undefined
								}
							})
							.then(
							function(response) {
								var data = response.data;
								var size = Object
								.keys(response.data).length;
								var lines = [];
								for (var i = 0; i < size; i++) {
									obj = {
											transactionReference : "",
											description : "",
											failedReason : ""
									};
									obj.transactionReference = data[i].transactionReference;
									obj.description = data[i].description;
									obj.failedReason = data[i].failedReason;
									lines.push(obj);
								}
								assignmentDataFactory
								.set(lines);
								console.log("lines [ "
										+ lines + " ]");
								$window.location.href = '/assignments/#!/assignments-result';
							},
							function(errResponse) {
								console
								.log("errorDetailsJSONString [ "
										+ JSON
										.stringify(errResponse.data)
										+ " ]")
										var data = errResponse.data;
								errorObject = {
										timestamp : data.timestamp,
										message : data.message,
										details : data.details
								};
								errorDataFactory
								.set(errorObject);
								$window.location.href = '/assignments/#!/error';
							});
				}
			}

		});
