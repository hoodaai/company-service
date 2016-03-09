'use strict';

/**
 * @ngdoc overview
 * @name clientApp
 * @description
 * # clientApp
 *
 * Main module of the application.
 */
angular
  .module('clientApp', [
    'ngRoute'
  ])
  .config(function ($routeProvider) {
      $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'CompanyController'
      })
      .when('/company/details/:name', {
        templateUrl: 'views/company-details.html',
        controller: 'CompanyController'
      })
      .when('/save', {
        templateUrl: 'views/main.html',
        controller: 'CompanyController'
      })
      .when('/update', {
        templateUrl: 'views/company-details.html',
        controller: 'CompanyController'
      })
      .otherwise({
        redirectTo: '/'
      });

  });
