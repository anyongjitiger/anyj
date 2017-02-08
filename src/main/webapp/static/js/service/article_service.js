'use strict';

angular.module('myApp').factory('ArticleService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = '/Article/';

    var factory = {
        fetchAllArticles: fetchAllArticles,
        deleteArticle:deleteArticle/*,
        createUser: createUser,
        updateUser:updateUser*/
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

    function createArticle(article) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, article)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating Article');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }


    function updateUser(user, id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+id, user)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating User');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function deleteArticle(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting Article');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

}]);