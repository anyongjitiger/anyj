'use strict';
arapp.controller('allArticleController', ['$scope', 'allArticleService', function($scope, ArticleService) {
	$scope.showAll=true;
	var self = this;
    self.article={id:null,title:'',content:'',date:''};
    self.articles=[];
    $scope.openUrl = function(val){
        window.location.href = '/Article/article/'+val.id;
    };
    if(op==""){
        fetchAllArticles();
    }else{
    	$scope.showAll=false;
    }
    function fetchAllArticles(){
    	ArticleService.fetchAllArticles()
            .then(
            function(d) {
                self.articles = d;
                $scope.news=d;
            },
            function(errResponse){
                console.error('Error while fetching Articles');
            }
        );
    }
}]);

arapp.controller('editArticleController', ['$scope', 'allArticleService', function($scope, ArticleService) {
	$scope.showEdit = true;
	$scope.edit = function(index,id){
    	window.location.href = '/Article/update/'+id;
    }
    $scope.delete = function(index,id){
    	var attrList = $scope.data;
    	for(var i=0,len=attrList.length;i<len;i++){
    	    if( attrList[i]&&(attrList[i].id==id)){
    	        attrList.splice(i,1);
    	    }
    	}
    	$scope.setData = function(){
            $scope.items = $scope.data.slice(($scope.pageSize * ($scope.selPage - 1)), ($scope.selPage * $scope.pageSize));//通过当前页数筛选出表格当前显示数据
        };
        $scope.items = $scope.data.slice(0, $scope.pageSize);
        $scope.selectPage($scope.selPage);
    	deleteArticle(id);
    }
    if(op=='edit'){
        fetchAllArticles();
    }else{
    	$scope.showEdit = false;
    }
    

    function deleteArticle(id){
    	ArticleService.deleteArticle(id)
            .then(function(){console.log('success delete')},
            function(errResponse){
                console.error('Error while deleting Article');
            }
        );
    }
    
    function fetchAllArticles(){
    	ArticleService.fetchAllArticles()
            .then(
            function(d) {
              //数据源
                $scope.data = d;
                //分页总数
                $scope.pageSize = 10;
                $scope.pages = Math.ceil($scope.data.length / $scope.pageSize); //分页数
                $scope.newPages = $scope.pages > 5 ? 5 : $scope.pages;
                $scope.pageList = [];
                $scope.selPage = 1;
                //设置表格数据源(分页)
                $scope.setData = function () {
                    $scope.items = $scope.data.slice(($scope.pageSize * ($scope.selPage - 1)), ($scope.selPage * $scope.pageSize));//通过当前页数筛选出表格当前显示数据
                }
                $scope.items = $scope.data.slice(0, $scope.pageSize);
                //分页要repeat的数组
                for (var i = 0; i < $scope.newPages; i++) {
                    $scope.pageList.push(i + 1);
                }
                $scope.selectPage = function (page) {
                    //不能小于1大于最大
                    if (page < 1 || page > $scope.pages) return;
                    //最多显示分页数5
                    if (page > 2) {
                        //因为只显示5个页数，大于2页开始分页转换
                        var newpageList = [];
                        for (var i = (page - 3) ; i < ((page + 2) > $scope.pages ? $scope.pages : (page + 2)) ; i++) {
                            newpageList.push(i + 1);
                        }
                        $scope.pageList = newpageList;
                    }
                    $scope.selPage = page;
                    $scope.setData();
                    $scope.isActivePage(page);
                };
                //设置当前选中页样式
                $scope.isActivePage = function (page) {
                    return $scope.selPage == page;
                };
                //上一页
                $scope.Previous = function () {
                    $scope.selectPage($scope.selPage - 1);
                }
                //下一页
                $scope.Next = function () {
                    $scope.selectPage($scope.selPage + 1);
                };
            },
            function(errResponse){
                console.error('Error while fetching Articles');
            }
        );
    }
}]);