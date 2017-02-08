'use strict';

angular.module('myApp').controller('ArticleController', ['$scope', 'ArticleService', function($scope, ArticleService) {
    var self = this;
    self.article={id:null,title:'',content:'',date:''};
    self.articles=[];
    self.remove = remove;
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
                console.error('Error while fetching Articles');
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
                console.error('Error while creating Article');
            }
        );
    }
    function remove(id){
        console.log('id to be deleted', id);
        /*if(self.user.id === id) {//clean form if the user to be deleted is shown there.
            reset();
        }*/
        deleteArticle(id);
    }
    function deleteArticle(id){
    	ArticleService.deleteArticle(id)
            .then(fetchAllArticles,
            function(errResponse){
                console.error('Error while deleting Article');
            }
        );
    }
}]);