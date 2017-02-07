'use strict';

angular.module('myApp').controller('ArticleController', ['$scope', 'ArticleService', function($scope, ArticleService) {
    var self = this;
    self.article={id:null,title:'',content:'',date:''};
    self.articles=[];
    $scope.openUrl = function(val){
        window.location.href = '/Article/article/'+val.id;
    };
    fetchAllArticles();

    function fetchAllArticles(){
    	ArticleService.fetchAllArticles()
            .then(
            function(d) {
                self.articles = d;
                $scope.news=d.slice(0,3);
            },
            function(errResponse){
                console.error('Error while fetching Users');
            }
        );
    }

    function createArticle(article){
    	ArticleService.createArticle(article)
            .then(
    		function(d) {
                console.log(d);
            },
            function(errResponse){
                console.error('Error while creating User');
            }
        );
    }
}]);