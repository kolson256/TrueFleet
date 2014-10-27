function testLogin($scope, $http) {
    $http({
       method: 'GET',
       url: 'http://localhost:8080/hello-world?name=Successful+Dropwizard+Users',
       headers: { }
    })
        .success(function(data) {
            $scope.loginResult = data;
        })
        .error(function(data) {
            $scope.errorResult = "There was an error.";
        });
}