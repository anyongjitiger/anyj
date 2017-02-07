'use strict';
arapp.controller('allArticleController', ['$scope', 'allArticleService', function($scope, ArticleService) {
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
                console.log(d);
                $scope.news=d;
            },
            function(errResponse){
                console.error('Error while fetching Users');
            }
        );
    }
}]);