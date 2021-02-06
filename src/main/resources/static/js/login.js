layui.use(['form', 'layer', 'jquery'], function() {
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : top.layer
	$ = layui.jquery;

	//登录操作
	form.on('submit(login)', function(data) {
		var d = data.field //名值对(key-vaule)
		$.ajax({
			type: "GET",
			url: "http://localhost:8080/home/login",
			dataType: 'json',
			data: d,
			success: function(res) {
				if(res.code==200){
					//跳转到index1界面（因为我们需要页面使用thymeleaf模版，因此所有的页面都要经过后台跳转）
					window.location.href="http://localhost:8080/home/system"

				}
				else {
					layer.msg(res.msg)
					changeCode();
				}
			}
		});
		return false;
	});

	//表单输入效果
	$(".loginBody .input-item").click(function(e) {
		e.stopPropagation();
		$(this).addClass("layui-input-focus").find(".layui-input").focus();
	})
	$(".loginBody .layui-form-item .layui-input").focus(function() {
		$(this).parent().addClass("layui-input-focus");
	})
	$(".loginBody .layui-form-item .layui-input").blur(function() {
		$(this).parent().removeClass("layui-input-focus");
		if($(this).val() != '') {
			$(this).parent().addClass("layui-input-active");
		} else {
			$(this).parent().removeClass("layui-input-active");
		}
	})
})

function changeCode() {
	var img = document.getElementById("codeImg");
	/*添加了时间戳，防止重复请求*/
	img.src = "http://localhost:8080/home/code?time=" + new Date().getTime();

}