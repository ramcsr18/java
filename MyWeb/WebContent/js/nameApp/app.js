var myNameApp = angular.module('myNameApp', ['ngRoute','NameController']);

myNameApp.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/show', {
		templateUrl : 'nameApp/showNames.html',
		controller : 'NameController'
	}).when('/add', {
		templateUrl : 'nameApp/addName.html',
		controller : 'NameController'
	}).otherwise({
		redirectTo : '/show'
	});
} ]);
