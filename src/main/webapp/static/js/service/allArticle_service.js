'use strict';
var arapp = angular.module('articleApp',[]);
arapp.factory('allArticleService', ['$http', '$q', function($http, $q){
    var REST_SERVICE_URI = '/Article/';

    var factory = {
        fetchAllArticles: fetchAllArticles
    };

    return factory;

    function fetchAllArticles() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Users');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

}]);