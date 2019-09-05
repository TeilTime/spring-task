/**
 * Created by Admin on 2019/2/26.
 */
/**
 * Created by Admin on 2019/2/26.
 */


import common from "./common.js";


class menu extends common{







    findMenu() {
        var para = {};
        para.url = "/manage/menu/getMenu"
        para.type = 'get';
        para.data = {};
        para.async = true;
        super.ajax(para).then(res=>{
            layui.use(['element','laytpl'], function(){
                layui.laytpl($("#menuTemplate").html()).render(res, function(html){
                    $("#menu").html(html);
                });
                var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
                element.init();
            });
        }).catch(res=>{

        })
    }
}
export default menu;



