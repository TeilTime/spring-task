<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<title>站点管理</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
<link rel="stylesheet" href="../../../public/js/layui/css/layui.css" media="all">
<link rel="stylesheet" href="../../../public/style/admin.css" media="all">
<script type="text/javascript" src="../../../public/js/jquery-2.2.2.min.js"></script>
<script type="text/javascript" src="../../../public/js/layui/layui.all.js"></script>

<div class="layui-card layadmin-header">
  <div class="layui-breadcrumb" lay-filter="breadcrumb">
    <a lay-href="">主页</a>
    <a><cite>应用</cite></a>
    <a><cite>站点管理</cite></a>
  </div>
</div>

<div class="layui-fluid">


  <div class="layui-fluid"  style="width: 76%;float: left;padding:0px 20px;box-sizing: border-box;">
    <div class="layui-card">


      <div class="layui-tab layui-tab-card">
        <ul class="layui-tab-title">
          <li class="layui-this">站点管理</li>
          <li>扩展字段</li>
          <li>工作流管理</li>
          <li>站点回收站</li>
        </ul>
        <div class="layui-tab-content" style="height: 600px;">
          <div class="layui-tab-item layui-show">
            <div class="layui-card-body">
              <div id="tree" class="demo-tree-more"></div>

            <script type="text/html" id="layuiadmin-app-cont-tagsbar">
              <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
              <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a>
            </script>
          </div>
          </div>
          <div class="layui-tab-item">暂无</div>
          <div class="layui-tab-item">暂无</div>
          <div class="layui-tab-item">
            <table id="LAY-app-content-tags" lay-filter="LAY-app-content-tags"></table>
            <%--<table id="demo" lay-filter="demo">
              <thead>
              <tr>
                <th lay-data="{type:'checkbox',field:'id'}">ID</th></i>
                <th lay-data="{field:'index', width:200}">序号</th>
                <th lay-data="{field:'name', width:200}">显示名称</th>
                <th lay-data="{field:'code', width:200}">唯一标识</th>
                <th lay-data="{field:'dataPath', width:200}">原位置</th>
                <th lay-data="{field:'lastModifyTime', width:200}">删除时间</th>
                <th lay-data="{field:'modify_by', width:200}">删除者</th>
                <th lay-data="{fixed: 'right', width:170, align:'center', toolbar: '#barDemo'}">操作</th>
              </tr>
              </thead>
            </table>--%>
            <div id="page" style="padding-left: 20px"></div>
          </div>


        </div>
      </div>
    </div>
    <div class="layui-form layui-card-header layuiadmin-card-header-auto" lay-filter="layadmin-userfront-formlist">
      <button class="layui-btn layuiadmin-btn-tags" data-type="export" onclick="doexport()">导出站点</button>
      <button type="button" class="layui-btn" id="upload"><i class="layui-icon"></i>导入站点</button>
    </div>
  </div>

</div>



<script>

    $(function(){
        getMetadatas();
        getDelDatas();
    })
    //导入站点
    layui.use('upload', function(){
        var $ = layui.jquery
            ,upload = layui.upload;


        //指定允许上传的文件类型
        upload.render({
            elem: '#upload'
            ,url: '/manage/site/import'
            ,accept: 'file' //普通文件
            ,done: function(res){
                console.log(res)
            }
        });
    });


    function doexport() {
        window.location.href="/manage/site/export?current=1&size=10";
    }

    //获取站点数据
    function getMetadatas(){
        //请求参数
        var jsondata = {};
        jsondata.status=0;

        $.ajax({
            type:"post",
            url:"/manage/site/getSiteList",
            contentType:'application/json',
            data:JSON.stringify(jsondata),
            dataType: 'json',
            success: function(data){
                showTree(data.obj);
                /*//console.log(data.obj[0].children[0].name)
                var i;
                var j;
                var str="";
                for(i=0;i<data.obj.length;i++){
                       str+="<tr><td>"+data.obj[i].name+"</td></tr>";
                    for (j=0;j<data.obj[i].children.length;j++){
                        str+="<tr><td width='20%'></td><td>"+data.obj[i].children[j].name+"</td></tr>";
                    }
                }
               $("#LAY-app-content-tags").html(str);*/
            },
            error: function() {
                alert("出错啦!!!")
            }
        })
    }


    function showTree(data) {
      layui.use('tree', function(){
          var tree = layui.tree;
          tree({
              elem: '#tree' //传入元素选择器
              ,nodes: data
              ,click: function(node){
                  console.log(node) //node即为当前点击的节点数据
                  $('#treeId').val( JSON.stringify(node));
//                    findUser(node.id);
              }
          });
      });
  }

  //获取回收数据
    function getDelDatas(){
        var jsondata = {
        "pager":{"current":1,"size":10},"entity":{"status":1}
        }
        $.ajax({
            type:"post",
            url:"manage/site/getDelList",
            contentType:'application/json',
            data:JSON.stringify(jsondata),
            dataType: 'json',
            success: function(data){
                if(data.success&&data.obj.records.length>0){
                    layui.use('table', function(){
                        var table = layui.table;
                        table.render({
                            elem: '#LAY-app-content-tags'
                            ,data:data.obj.records
                            ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
                            ,cols: [[
                                {type:'checkbox',field:"id"}
                                ,{type:'numbers',title:'序号'}
                                ,{field:'name', title: '显示名称'}
                                ,{field:'code', title: '唯一标识'}
                                ,{field:'dataPath',title:'原位置'}
                                ,{field:'lastModifyTime', title: '删除时间', width: '30%', minWidth: 100} //minWidth：局部定义当前单元格的最小宽度，layui 2.2.1 新增
                                ,{field:'modify_by',title:'删除者'}
                            ]]
                        });
                        table.on('row(LAY-app-content-tags)', function(obj){
                            var data = obj.data;
                            //标注选中样式
                            obj.tr.addClass('layui-table-click').siblings().removeClass('layui-table-click');
                        });
                    });
                }
            },
            error: function() {
                alert("出错啦!!!")
            }
        })
    }

</script>