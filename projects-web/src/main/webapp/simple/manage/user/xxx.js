import  base from "../../../public/js/common/base.js";
import conslog from "../../../public/js/common/conslog.js";
import common from "../../../public/js/common/common.js";
import menu from "../../../public/js/common/menu.js";



var cons = new conslog();
var url = "/manage/menu";
var baseOrder = [];
var tempSize = 10;

$(function(){
    var tempMenu = new menu();

    tempMenu.findMenu();
    TempdoList(1,tempSize,"id",true);
    tempFindMenu()

    $('#searchButton').click(function() {
        TempdoList(1,tempSize,"id",true);
    })

    $(document).on('click','#treeEditBtn',function(){
        var data = JSON.parse($('#treeId').val())
        if(data=='0'){
            layui.layer.msg("请选择需要修改的数据！");
            return false;
        }
        setOpenData( data,1);
    })
    $(document).on('click','#treeAddBtn',function(){
        var data = JSON.parse($('#treeId').val());
        var data1 = {};
        if(data==0){
            data1.id=0;
            setOpenData( data1,2);
        }else{
            setOpenData( data,2);
        }
    })
    $(document).on('click','#treeDelBtn',function(){
        var data = JSON.parse($('#treeId').val());
        if(data==0){
            layui.layer.msg('请选择需要删除的数据!');
            return false;
        }else{
            doTopDelete(data,1);
        }
    })

});

//新增数据方法
function doAdd(data){
    let tempBase = new base(url,JSON.stringify(data),"");
    tempBase.add().then(res=>{
        $("#tempMsg").text(res.msg);
    }).catch(res=>{

    })
}
//更新方法
function doUpdate(data){
    let tempBase = new base(url,JSON.stringify(data),data.id);
    tempBase.updata().then(res=>{
        $("#tempMsg").text(res.msg);
    }).catch(res=>{

    })
}
//根据id查询数据
function doView() {
    let tempBase = new base(url,"","1");
    tempBase.view().then(res=>{
        var temp = [];
        temp.push(res.obj);
        setTableData(temp);
        cons.consoleLog(temp);
    }).catch(res=>{

    })

}
//删除
function doDelete() {
    let tempBase = new base(url,user,1);
    tempBase.delete().then(res=>{
        cons.consoleLog(res)
    }).catch(res=>{

    })
}
//根据ids批量删除
function doDeleteByIds(data) {
    let tempBase = new base(url,data,1);
    tempBase.deleteByIds().then(res=>{
        cons.consoleLog(res)
        $("#tempDel").text(res.msg);
    }).catch(res=>{

    })
}

function tempFindMenu() {
    let comm = new common();
    var para = {};
    para.url = url+"/getMenu"
    para.type = 'get';
    para.data = {};
    para.async = true;
    comm.ajax(para).then(res=>{
        showTree(res.obj);
    }).catch(res=>{

    })

}
//条件列表查询
function doList(current,size,cloum,sort) {
    var data = getSearchCondition();
    var sortProps = [];
    if(baseOrder.length>0){
        sortProps = baseOrder;
    }else{
        sortProps.push({key:cloum,value:sort});
    }
    data.pager = {current:current,size:size};
    data.pager.sortProps = sortProps;
    let tempBase = new base(url,JSON.stringify(data),"");
    tempBase.list().then(res=>{
        cons.consoleLog(res);
////            if(current==1){
//                page(res.obj.total);
////            }
        setTableData(res.obj.records);
    }).catch(res=>{

    })
}

//条件列表查询
function TempdoList(current,size,cloum,sort) {
    var sortProps = [];
    var data=getSearchCondition();
    if(baseOrder.length>0){
        sortProps = baseOrder;
    }else{
        sortProps.push({key:cloum,value:sort});
    }
    data.pager = {current:current,size:size};
    data.pager.sortProps = sortProps;
    let tempBase = new base(url,JSON.stringify(data),"");
    tempBase.list().then(res=>{
        cons.consoleLog(res);
        page(res.obj.total);
        setTableData(res.obj.records);
    }).catch(res=>{

    })
}

//    layui.use(['form', 'upload'], function(){  //如果只加载一个模块，可以不填数组。如：layui.use('form')
//
//
//
//
//
//    });

//设置弹出层数据（新增，查看，修改）
function setOpenData(data,type) {
    data.disable = type;
    layui.layer.open({
        type: 1,
        title:"数据<span style='color: red;float:right' id='tempMsg'>",
        area:['500px','500px'],
        closeBtn: 0, //关闭按钮（0：不显示，1：显示）
        anim: 2,
        shadeClose: true, //开启遮罩关闭
        btn: ['确定','取消'],
        content: '<div id="table_data"></div>',
        yes: function(index, layero){
            if(type!=0){
                getOpenData(data);
            }else{
                layui.layer.close(index);
            }
        },
        btn2:function (index,layero) {
            if(type!=0){
                location.reload();
            }
        },
        end:function () {
            if(type!=0){
                location.reload();
            }
        }

    });
    layui.laytpl($("#tableDataTemplate").html()).render(data, function(html){
        $("#table_data").html(html)
    });
}

//弹出层渲染（删除）
function setDeleData(data) {
    layui.layer.open({
        type: 1,
        title:"删除数据 <span style='color: red;float:right' id='tempDel'>",
        area:['400px','200px'],
        closeBtn: 0, //关闭按钮（0：不显示，1：显示）
        anim: 2,
        shadeClose: true, //开启遮罩关闭
        btn: ['确定','取消'],
        content: '<div id="table_del"></div>',
        yes: function(index, layero){
            doDeleteByIds(data);
        },
        btn2:function (index,layero) {
            location.reload();
        },
        end:function () {
            location.reload();
        }

    });
    layui.laytpl($("#delTableTemplate").html()).render(data, function(html){
        $("#table_del").html(html)
    });
}

//渲染数据到table
function setTableData(data) {
    var temp1 = "id";
    var temp2 ="asc";
    if(baseOrder.length>0){
        temp1 = baseOrder[0].key;
        if(baseOrder[0].value==true){
            temp2 = "asc";
        }else{
            temp2 = "desc"
        }
    }
    layui.use('table', function(){
        var table = layui.table;
        table.init('demo', {
            height: 550 //设置高度
            ,limit: tempSize //注意：请务必确保 limit 参数（默认：10）是与你服务端限定的数据条数一致
            //支持所有基础参数
            ,data:data
            ,skin: 'line' //表格风格
            ,even: true
//                ,limits: [5, 7, 10]
            ,autoSort:false
//                ,toolbar: '#toolbarDemo'
            ,initSort: {
                field: temp1 //排序字段，对应 cols 设定的各字段名
                ,type: temp2 //排序方式  asc: 升序、desc: 降序、null: 默认排序
            }
        });
    });
}



//获取删除操作的数据
function doTopDelete(data1,type) {
    var data = {};
    var ids = "";
    if(type==2){
        /* <![CDATA[ */
        for(var i=0;i<data1.length;i++){
            ids = ids + data1[i].id + ","
        }
        /* ]]> */
        data.ids = ids.substring(0,ids.length-1);
    }else{
        data.ids = data1.id;
    }
    setDeleData(data);
}

//渲染分页
function page(total) {
    layui.use('laypage', function(){
        var laypage = layui.laypage;
        laypage.render({
            elem: 'page1'
            ,count: total //数据总数，从服务端得到
            ,layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip']
            ,jump: function(obj, first){
                //obj包含了当前分页的所有参数，比如：
                cons.consoleLog(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
                cons.consoleLog(obj.limit); //得到每页显示的条数
                tempSize = obj.limit;
                //首次不执行
                if(!first){
                    //do something
                    doList(obj.curr,obj.limit,"id",true);
                }
            }
        });
    });
}
//弹出层按钮触发
function getOpenData(data1) {
    var disable = $(".disable").val();
    if(disable==0){
        return false;
    }

    var data = {};
    var list = $(".active");
    /* <![CDATA[ */
    for(var i=0;i<list.length;i++) {
        if(list[i].checked) { //取到对象数组后，我们来循环检测它是不是被选中
            data.active = list[i].value;
        }
    }
    /* ]]> */
    data.name = $(".name").val();
    data.naturalId = $(".naturalId").val();
    data.description = $(".description").val();
    data.photoUrl = $(".photoUrl").val();
    data.url = $(".url").val();

    if(disable==1){
        data.id = $(".id").val();
        doUpdate(data);
    }else{
        data.parentId = data1.id;
        doAdd(data);
    }
}



function getSearchCondition() {
    var data = {};
    var entity = {}
    entity.name = $("#name").val();
    data.entity = entity;
    return data;
}

//渲染树结构
function showTree(data) {

    layui.use('tree', function(){
        var tree = layui.tree;
        tree({
            elem: '#demoTree' //传入元素选择器
            ,nodes: data
            ,click: function(node){
                console.log(node) //node即为当前点击的节点数据
                $('#treeId').val( JSON.stringify(node));
//                    findUser(node.id);
            }
        });
    });
}