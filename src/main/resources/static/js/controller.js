var app = angular.module("app", [ 'checklist-model' ]);
app.controller('controller', function($scope, $http) {
	/* $scope.clickfunction = function(){
	   var time = $filter('date')(new Date(),'yyyy-MM-dd HH:mm:ss Z');
	   $scope.welcome = "Time = " + time;    
	 }
	 */

	$scope.onClickOptions = function() {
		$scope.visible = !$scope.visible;
	}
	$scope.dependencies = [{"displayValue":"Rabbit MQ","value":"rabbitmq"},{"displayValue":"Spring Web","value":"web"}];
	$scope.java = {
		language : 'java',
		project : '',
		version : '2.2.0.RELEASE',
		groupId : '',
		artifactId : '',
		projectName : '',
		projectDesc : '',
		packageName : '',
		packaging : '',
		javaVersion : '1.8',
		domainName: '',
		dependencies : []
	};

	$scope.projectInitForm = function() {
		// $scope.java = angular.copy($scope.java);
		console.log("dependencies selected are ", $scope.java.dependencies);
		if ($scope.java.projectName == '') {
			$scope.java.projectName = $scope.java.artifactId;
			console.log("$scope.java.projectName: " + $scope.java.projectName);
		}
		if ($scope.java.packageName == '') {
			$scope.java.packageName = $scope.java.groupId;
			console.log("$scope.java.packageName: " + $scope.java.packageName);

		}

		var params ={
			type : $scope.java.project,
			language : $scope.java.language,
			bootVersion : $scope.java.version,
			baseDir : $scope.java.projectName,
			groupId : $scope.java.groupId,
			artifactId : $scope.java.artifactId,
			domainName : $scope.java.domainName,
			name : $scope.java.projectName,
			description : $scope.java.projectDesc,
			packageName : $scope.java.packageName,
			packaging : 'jar',
			javaVersion : $scope.java.javaVersion,
			dependencies : 'web'
		}
		var urlParameters = Object.entries(params).map(e => e.join('=')).join('&'); 
		console.log("url ==> ",urlParameters);
		/*$http.post('/java', $scope.java).then(function (response) {
			alert('success'+ response.data.language);
		}, function (error) {
			alert('error');
		});*/
			 var xhr = new XMLHttpRequest();
			  xhr.open("GET", "/spring-micro?"+urlParameters);
			  xhr.setRequestHeader("Content-Type", "application/octet-stream");
			  xhr.responseType = "blob";
			  xhr.onload = function () {
			      if (this.status === 200) {
			          var blob = new Blob([xhr.response], {type: "application/zip"});       
			          var a = document.createElement('a');
			             a.href = URL.createObjectURL(blob);
			             a.download = $scope.java.projectName+".zip";
			             a.click();
			      }
			  };
			  xhr.send();
			  

		/*$http({
			url : "/spring-micro",
			method : "GET",
			responseType: "blob",
			params : {
				type : $scope.java.project,
				language : $scope.java.language,
				bootVersion : $scope.java.version,
				baseDir : $scope.java.projectName,
				groupId : $scope.java.groupId,
				artifactId : $scope.java.artifactId,
				name : $scope.java.projectName,
				description : $scope.java.projectDesc,
				packageName : $scope.java.packageName,
				packaging : 'jar',
				javaVersion : $scope.java.javaVersion,
				dependencies : 'web'
			}
		}).then(function(response) {
			var linkElement = document.createElement('a');
			try {
				var blob = new Blob([ data ], {
					type : "application/octet-stream"
				});
				linkElement.href = URL.createObjectURL(blob);
				linkElement.download = "appName.zip";
				linkElement.click();
			} catch (ex) {
				console.log(ex);
			}
		});*/
	}
});

/*
 * app.controller('controller', function($scope, $filter) { $scope.clickoptions =
 * function(){ $scope.visible=!$scope.visible; }
 * 
 * $scope.onclickjava = function() {
 * 
 * 
 * if ($("#languagejava").is(':checked')){
 * document.getElementById("java").style.visibility = "visible";
 * $("#java").fadeIn(200); }else{ $("#java").fadeOut(200);
 * document.getElementById("java").style.visibility ="hidden"; }
 *  }
 * 
 * 
 * });
 */
/*app.controller('controller', function($scope, $filter) {
 $scope.java ={
 language:'',
 project:'',
 version:'',
 metadata:{
 group:'',
 artifact:''
 }

 });*/

