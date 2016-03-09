'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:CompanyService
 * @description
 * # CompanyService
 *
 */

 'use strict';

 angular.module('clientApp')
   .service('companyService', function ($http, $log) {
     // AngularJS will instantiate a singleton by calling "new" on this function
    return {

 	    createCompany: function(company) {
 	        $log.debug("create company");
            var request = $http.post('http://localhost:8080/api/company/', company);
 	        return request;
 	    },

        updateCompany: function(company) {
           $log.debug("update company");
           var request = $http.put('http://localhost:8080/api/company/', company);
           return request;
       },

        getAllCompany: function() {
            $log.debug("getting list of companies");
            var request = $http.get('http://localhost:8080/api/companies');
            return request;
        },

 	    getCompanyDetails: function(companyName) {
 	        $log.debug("get company details");
 	        var request = $http.get('http://localhost:8080/api/company/' + companyName);
 	        return request;
 		},

        addBeneficialOwner: function(companyName, beneficialOwner) {
         	$log.debug("add beneficial owner" +beneficialOwner.name);
            var request = $http.post('http://localhost:8080/api/company/' + companyName, beneficialOwner);
         	return request;
         }
    };

   });
