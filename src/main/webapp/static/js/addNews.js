/**
 * Created by i on 2016/12/30.
 */
$(function () {
    var preUrl='/Article/';
    //editor begin----------------------------------------
    // 阻止输出log
    wangEditor.config.printLog = false;
    var editor = new wangEditor('editor-trigger');

    // 上传图片
    editor.config.uploadImgUrl = '/upload/';
    editor.config.uploadHeaders = {
        'Accept' : 'text/x-json'
    }
    editor.config.uploadImgFileName = 'myFileName';

    // 隐藏网络图片
    editor.config.hideLinkImg = true;

    // 表情显示项
    editor.config.emotionsShow = 'value';
    editor.config.emotions = {
        'default': {
            title: '默认',
            data: 'static/dist/emotions.data'
        },
        'weibo': {
            title: '微博表情',
            data: [
                {
                    icon: 'http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/7a/shenshou_thumb.gif',
                    value: '[草泥马]'
                },
                {
                    icon: 'http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/60/horse2_thumb.gif',
                    value: '[神马]'
                },
                {
                    icon: 'http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/bc/fuyun_thumb.gif',
                    value: '[浮云]'
                },
                {
                    icon: 'http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/c9/geili_thumb.gif',
                    value: '[给力]'
                },
                {
                    icon: 'http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/f2/wg_thumb.gif',
                    value: '[围观]'
                },
                {
                    icon: 'http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/70/vw_thumb.gif',
                    value: '[威武]'
                }
            ]
        }
    };
    editor.create();
    //editor end------------------------------------------
    $('#btn1').click(function () {
        // 获取编辑器区域完整html代码
        var html = editor.$txt.html();

        // 获取编辑器纯文本内容
        var text = editor.$txt.text();

        // 获取格式化后的纯文本
        var formatText = editor.$txt.formatText();
        var title = $('#biaoti').val();
        if($.trim(title)==''){
        	alert("请输入标题");
        	return;
        }
        if($.trim(articleId)!=""){
            saveEditor(title,'',html,'update');
        }else{
        	saveEditor(title,'',html,'create');
        }
    });
    $('#btn2').click(function(){
        $('#biaoti').val('');
        console.log(articleinfo);
        editor.$txt.html('<p><br></p>');
    });
    function saveEditor(title,url,content,todo){
        if(todo=='create'){
        	$.ajax({
	            url: preUrl+"create/",
	            type: "post",
	            async: false,
	//            dataType: "JSON",
	            contentType: "application/json",
	            data: JSON.stringify({'title':title,'url':'','content':content}),
	            success: function(data, textStatus) {
	            	if(textStatus=='success'){
	            		$('.popup').css({
	            			left: ($(window).width() - $('.popup').outerWidth())/2, 
	            			top: ($(window).height() - $('.popup').outerHeight())/2
	            		});
	            		$('.popup').show();
	            		$('.popup').fadeOut(2000);
	            		setTimeout("window.location.href = '/Article/allNews/'",2000);
	            	}
	        	},
	            error:function(result){ console.log(result); }
	        });
        }else if(todo=="update"){
        	$.ajax({
	            url: preUrl+articleId+"/",
	            type: "PUT",
	            async: false,
	//            dataType: "JSON",
	            contentType: "application/json",
	            data: JSON.stringify({'title':title,'url':'','content':content}),
	            success: function(data, textStatus) {
            		console.log(textStatus);
	            	if(textStatus=='success'){
	            		$('.popup').css({
	            			left: ($(window).width() - $('.popup').outerWidth())/2, 
	            			top: ($(window).height() - $('.popup').outerHeight())/2
	            		});
	            		$('.popup').show();
	            		$('.popup').fadeOut(2000);
	            		setTimeout("window.location.href = '/Article/allNews/'",2000);
	            	}
	        	},
	            error:function(result){ console.log(result); }
	        });
        }
    }
});
