var testEditor;

$(function() {
	testEditor = editormd("test-editormd", {
		width : "100%",
		height : 300,
		syncScrolling : "single",
		path : "/editor/lib/",
		emoji : true, // 表情
		sequenceDiagram : true, // 时序图、序列图
		taskList : true, // GFM 任务列表
		flowChart : true, // 流程图
		tex : true, // Tex 科学公式语言
		imageUpload : true,
		imageFormats : [ "jpg", "jpeg", "gif", "png", "bmp", "webp" ],
		imageUploadURL : "/file",
		// 这个配置在simple.html中并没有，但是为了能够提交表单，使用这个配置可以让构造出来的HTML代码直接在第二个隐藏的textarea域中，方便post提交表单。
		saveHTMLToTextarea : true,
		toolbarIcons : function() {
			return [ "undo", "redo", "|", "bold", "del", "italic", "quote",
					"|", "h1", "h2", "h3","|", "list-ul", "list-ol", "hr", 
					"|", "link","reference-link", "image", "file", "code",
					"preformatted-text", "code-block", "|", "watch",
					"preview", "help" ]
		},
		// 用于增加自定义工具栏的功能，可以直接插入HTML标签，不使用默认的元素创建图标
		toolbarIconsClass : {
			// home : "fa-home",
			file : "fa-file",
		},

		// 用于增加自定义工具栏的功能，可以直接插入HTML标签，不使用默认的元素创建图标
		toolbarCustomIcons : {
		// title : "<img src='/editor/images/software.png'
		// style='height:30px'>",
		},

		// 自定义工具栏按钮的事件处理
		toolbarHandlers : {
			/**
			 * @param {Object}
			 *            cm CodeMirror对象
			 * @param {Object}
			 *            icon 图标按钮jQuery元素对象
			 * @param {Object}
			 *            cursor CodeMirror的光标对象，可获取光标所在行和位置
			 * @param {String}
			 *            selection 编辑器选中的文本
			 */
			file : function() {
				$('#myModal').modal('show');
			},
		/*
		 * home : function() {
		 * window.location.href="http://localhost:8080/myEditor"; },
		 */
		},

		lang : {
			toolbar : {
				// home : "我的创作",
				file : "上传文件",
			}
		}
	});
});

$('#file').on('change', function() {
	var file = $("#file").val();
	var fileName = getFileName(file);
	$('#text').val(fileName);
});

function upload() {
	var file = $("#file").val();
	if (file == null || file == "") {
		alert("请选择文件！");
	} else {
		var data = new FormData($('#excelForm')[0]);
		$.ajax({
			type : "post",
			url : "/file",
			data : data,
			processData : false, // 不序列化数据
			contentType : false, // multipart request
			success : function(data) {
				$('#myModal').modal('hide');
				//alert(data.message);
				var fileName = getFileName(file);
				var ht = "[" + fileName + "](" + data.url + ")";
				testEditor.insertValue(ht);
				//$('#myModal').modal('hide');
			},
			error : function(data) {
				alert("上传失败！");
			}
		});
	}
}

function getFileName(o) {
	var pos = o.lastIndexOf("\\");
	return o.substring(pos + 1);
}