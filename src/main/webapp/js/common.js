function checkChecked(table){
    var data = getTableCheck(table);
    if(data.length <=0){
        layer.alert("请选择数据");
        return false;
    }else {
        var ids = [];
        for(var i in data){
            ids.push(data[i].id);
        }
        return ids;
    }
}
function getTableCheck(table){
    var checkStatus = layui.table.checkStatus(table)
        ,data = checkStatus.data;
    return data;
}
function getTableEdit(table){
    var data = getTableCheck(table);
    if(data.length <=0){
        layer.alert("请选择数据");
        return false;
    }else {
        return data[0];
    }
}
function ajax (url,type,data) {
    var index = layer.load();
    return new Promise((resovle, reject) => {
        $.ajax({
            "type": type,
            // "async": para.async,
            "url": url,
            contentType: 'application/json',
            "data": data,
            dataType: 'json',
            "success": res => {
                layer.close(index);
                resovle(res);
            },
            "error": err => {
                reject(err);
            }
        })
    })
}
function ajax2 (url,type,data) {
    var index = layer.load();
    return new Promise((resovle, reject) => {
        $.ajax({
            "type": type,
            "url": url,
            "data": data,
            dataType: 'json',
            "success": res => {
                layer.close(index);
                resovle(res);
            },
            "error": err => {
                reject(err);
            }
        })
    })
}
$(document).on('click', '.layui-btn',function(){
    var type = $(this).data('type');
    active[type] ? active[type].call(this) : '';
});
var active = {
    del: function(){
        del();
    },
    delAll: function(){
        delAll();
    },
    add: function () {
        add();
    },
    edit: function() {
        edit();
    },
    close: function(){
        layer.closeAll();
    }
};
//渲染分页
function page(total,curr,size) {
    layui.laypage.render({
        elem: 'page'
        ,limit:size
        ,curr:curr
        ,count: total //数据总数，从服务端得到
        ,layout: ['count', 'prev', 'page', 'next', 'refresh', 'skip']
        ,jump: function(obj, first){
            if(!first){
                doList(obj.curr,{});
            }
        }
    });
}
//渲染分页
function page2(total,curr,size) {
    layui.laypage.render({
        elem: 'page2'
        ,limit:size
        ,curr:curr
        ,count: total //数据总数，从服务端得到
        ,layout: ['count', 'prev', 'page', 'next', 'refresh', 'skip']
        ,jump: function(obj, first){
            if(!first){
                doList2(obj.curr);
            }
        }
    });
}
