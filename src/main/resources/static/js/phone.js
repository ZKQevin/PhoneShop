layui.use(['table', 'layer'], function() { /*function(){}是回调函数*/
    var table = layui.table; //数据表格
    $ = layui.jquery; //jquery
    layer = layui.layer; //弹框
    //执行一个 table 实例化一个数据表格
    table.render({
        elem: '#musictable',
        height: 600,
        url: 'http://localhost:8080/tbPhone/queryAllByLimit' //数据接口
        ,
        title: '手机列表',
        page: true //开启分页
        ,
        /*
        toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
            ,
        totalRow: true //开启合计行
            ,*/
        cols: [
            [ //表头
                { //表头 field必须和返回的JSON数据的key值一模一样
                    field: 'id',
                    /*必须跟实体类entity中的名字一一对应*/
                    title: 'ID',
                    sort: true, //排序
                    fixed: 'left' //固定在左边
                }, {
                field: 'type',
                title: '品牌',
                edit: 'text',
                align: 'center'
            }, {
                field: 'name',
                title: '型号',
                edit: 'text',
                align: 'center'
            }, {
                field: 'image',
                title: '手机图片',
                align: 'center',
                templet: function(res) {
                    return '<img src="' + res.image+ '" style="width:40px;height:40px"/>'
                }
            }, {
                field: 'intro',
                title: '介绍',
                edit: 'text',
                align: 'center'
            }, {
                field: 'price',
                title: '价格',
                edit: 'text',
                align: 'center'
            }, {
                field: 'recom',
                title: '推荐'
            }, {
                field: 'act',
                title: '活动价'
            }, {
                title: "操作",
                fixed: 'right',
                align: 'center',
                toolbar: '#barDemo'
            }
            ]
        ]
    });

    //监听行工具事件
    table.on('tool(musicfilter)', function(obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data //获得当前行数据
            ,
            layEvent = obj.event; //获得 lay-event 对应的值
        if(layEvent === 'del') {
            layer.msg('删除操作');
            layer.confirm('真的删除行么', function(index) {
                /*obj.del(); //删除对应行（tr）的DOM结构（这只是从前端框架中删除了，后端没有删除，后面写ajax才可以删除）
                layer.close(index);*/
                //向服务端发送删除指令 AJAX发送异步请求 需要使用jquery
                $.ajax({
                    type: "DELETE", //请求方式
                    url: "http://localhost:8080/tbPhone/deleteById/" + data.id,
                    success: function(res) {
                        //请求成功以后，后台返回的数据封装到ResponseData-->res
                        //==是判断值相等
                        //===绝对相等	数据类型必须一致并且值也一致
                        if(res.code == 200) {
                            layer.alert(res.msg);
                            obj.del(); //删除对应行（tr）的DOM结构
                            layer.close(index);
                        } else {
                            layer.alert(res.msg);
                        }
                    }
                });
            });
        }
    });

    //监听单元格编辑
    table.on('edit(musicfilter)', function(obj) { //update tb_music set name=1 where id=data.id
        var value = obj.value //得到修改后的值
            ,
            data = obj.data //得到所在行所有键值	data.id
            ,
            field = obj.field; //得到字段
        /*layer.msg('[ID: ' + data.id + '] ' + field + ' 字段更改为：' + value);*/

        //向服务器发送更新请求
        $.ajax({
            type: "PATCH",
            url: "http://localhost:8080/tbPhone/updatephone",
            dataType: 'json', //规定请求成功以后，后台返回的数据格式必须是json
            //data是前端向后台传送的参数（必须是JSON格式）
            contentType: 'application/json',
            data: JSON.stringify({
                "value": value,
                "id": data.id,
                "field": field
            }),
            success: function(res) {
                if(res.code == 200) {

                    layer.alert(res.msg);
                } else {
                    layer.alert(res.msg);
                }
            }
        });
    });

})