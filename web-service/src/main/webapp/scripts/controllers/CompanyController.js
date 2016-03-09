'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:CompanyController
 * @description
 * # CompanyController
 * Controller of the clientApp
 */

angular.module('clientApp')
  .controller('CompanyController', function ($scope, $location, companyService) {

            companyService.getAllCompany().success(function(data) {
    		  	console.log("Success");
    		  	$scope.companyList = data;
    		}).error(function(data) {
    		  	console.log("error");
            });

            var id = $location.url().split('/')[2];
              if(id === 'details') {
                  companyService.getCompanyDetails($location.url().split('/')[3]).then(
                   function(result) {
                      console.log(result.data.name);
                       $scope.company = result.data;
                   });
              };

              $scope.updateCompany = function(company, form) {
                  if(company.name && company.email) {
                      companyService.updateCompany(company).success(function(data) {
              		  	console.log("Success");
              		  	$scope.companyList = data;
              		    }).error(function(data) {
              		  	    console.log("error");
                        });
                  }

              };

              $scope.addBeneficialOwner = function(beneficialOwner) {
                  console.log("companyId "+$scope.company.companyId);
                   if(beneficialOwner.name) {
                      companyService.addBeneficialOwner($scope.company.companyId, beneficialOwner).success(function(data) {
                       console.log("Success");
                       $scope.company.beneficialOwner.push({"id": $scope.company.companyId, "name":beneficialOwner.name});
                       $scope.beneficialOwner.name="";
                   }).error(function(data) {
                       console.log("error");
                      });
                  }

              };

              $scope.createCompany = function(company, form) {
                  if(company.name && company.email) {
                      console.log(company.name);
                      companyService.createCompany(company).success(function(data) {
                       console.log("Success");
                       $scope.companyList.push(company);
                       }).error(function(data) {
                           console.log("error");
                    });
                  }

              };
  });
