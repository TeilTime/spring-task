<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<title>分类法管理</title>

<div class="layui-card layadmin-header">
  <div class="layui-breadcrumb" lay-filter="breadcrumb">
    <a lay-href="">主页</a>
    <a><cite>应用</cite></a>
    <a><cite>分类法管理</cite></a>
  </div>
</div>

<div class="layui-fluid">

  <div class="layui-col-md9">
    <div class="layui-card">
      <div class="layui-card-header layuiadmin-card-header-auto">
        <button class="layui-btn layuiadmin-btn-tags" data-type="add">新建分类法</button>
        <button class="layui-btn layuiadmin-btn-tags" data-type="export" onclick="doexport()">导出分类法</button>
        <button class="layui-btn layuiadmin-btn-tags" data-type="update">维护分类法</button>
        <button class="layui-btn layuiadmin-btn-tags" data-type="delete">删除分类法</button>
        <button id="upload" class="layui-btn layuiadmin-btn-tags" data-type="import">导入分类法</button>
      </div>
      <div class="layui-card-body">
        <table id="LAY-app-content-tags" lay-filter="LAY-app-content-tags"></table>
        <script type="text/html" id="layuiadmin-app-cont-tagsbar">
          <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
          <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a>
        </script>
      </div>
    </div>
  </div>
</div>

<script>


    $(function(){
        getMetadatas();
    })

    layui.use('upload', function(){
        var $ = layui.jquery
            ,upload = layui.upload;
        //指定允许上传的文件类型
        upload.render({
            elem: '#upload'
            ,url: '/metadata/class/import'
            ,accept: 'file' //普通文件
            ,done: function(res){
                console.log(res)
            }
        });
    });

  function doexport() {

      window.location.href="/metadata/class/exportList?current=1&size=10";

  }



  function getMetadatas(){
      var jsondata = {
          "pager": {
              "current": 1,
              "size": 10
          }
      }
      $.ajax({
          type:"post",
          url:"/metadata/class/list",
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
                              ,{field:'className', title: '名称', sort: true}
                              ,{field:'classDesc', title: '描述'}
                              ,{field:'cruser',title:'创建人'}
                              ,{field:'crtime', title: '创建时间', width: '30%', minWidth: 100} //minWidth：局部定义当前单元格的最小宽度，layui 2.2.1 新增
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