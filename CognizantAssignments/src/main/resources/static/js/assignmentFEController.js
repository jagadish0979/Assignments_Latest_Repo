//AssignmentFEController.js

angular
.module('app')
.controller(
		'AssignmentFEController',
		function($scope, $location, $window, assignmentFEDataFactory,
				errorDataFactory) {
			var vm = this;
			var obj = {
					firstName : "",
					surName : "",
					issueCount : 0,
					dateOfBirth : new Date()
			};

			$scope.fileContent = '';
			$scope.processFile = function() {
				var file = document.getElementById("assignmentFEFile").files[0];
				if (file) {
					var fileReader = new FileReader();
					fileReader.readAsText(file, "UTF-8");
					fileReader.onload = function(evt) {
						try {
							var expectedHeaders = [ "First name", "Sur name", "Issue count", "Date of birth" ];
							$scope.fileContent = fileReader.result;
							console.log("fileContent [ "
									+ $scope.fileContent + " ]");
							var allTextLines = $scope.fileContent
							.split(/\r\n|\n/);
							
							var headerLine = allTextLines[0];
							 for (var h = 0; h < headerLine.length; h++) {
								 headerLine = headerLine.replace("\"","");
								 }
							var headers = headerLine.split(',');
							console.log("headers [ " + headers + " ] ");
							if (expectedHeaders.length == headers.length) {
								for (var i = 0; i < expectedHeaders.length; ++i) {
							        if (headers[i] !== expectedHeaders[i]) {
										throw new Error("Invalid File");
							        }
							    }
								var lines = [];
								for (var i = 1; i < allTextLines.length; i++) {
									// split content based on comma
									var data = allTextLines[i]
									.split(',');
									if (data.length == headers.length) {
										obj = {
												firstName : "",
												surName : "",
												issueCount : 0,
												dateOfBirth : new Date()
										};

										obj.firstName = data[0]
										.replace(/"/g, '');
										obj.surName = data[1].replace(
												/"/g, '');
										obj.issueCount = data[2]
										.replace(/"/g, '');
										obj.dateOfBirth = data[3]
										.replace(/"/g, '');
										lines.push(obj);
									}
								}
								assignmentFEDataFactory.set(lines);
								console.log("lines [ " + lines + " ]");
								$window.location.href = '/assignments/#!/assignments-fe-result';
							}
							else{
								throw new Error("Invalid File");
							}
							
						} catch (e) {
							var data = {
									message : "Invalid File"
							};
							errorDataFactory.set(data);
							$window.location.href = '/assignments/#!/error';
						}
					}

				}
			}

		});
