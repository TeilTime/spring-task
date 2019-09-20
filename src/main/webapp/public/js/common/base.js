/**
 * Created by Admin on 2019/2/27.
 */


import common from "./common.js";
import baseurl from "./baseurl.js"


class base extends  common{
    /**
     *
     * @param url   url (controller的mapping映射+/)
     * @param data  向后台传送的数据（没有传""）
     * @param id   数据id (没有传"")
     */
    constructor(url,data,id) {
        super();// 调用父类的constructor(x, y)
        this.baseurl = new baseurl(url,data,id);
    }

    /**
     * 新增数据方法
     */
    add(){
        return this.ajax(this.baseurl.add());
    }

    /**
     * 更新方法
     * @returns {Promise}
     */
    updata(){
        return this.ajax(this.baseurl.updata())
    }
    //根据id查询
    view(){
        return this.ajax(this.baseurl.view());
    }
    //根据id删除数据
    delete(){
        return this.ajax(this.baseurl.delete());
    }
    deleteByIds(){
        return this.ajax(this.baseurl.deleteByIds());
    }


    //条件列表查询
    list(){
        return this.ajax(this.baseurl.list());
    }
    //查询列表
    getAll(){
        return this.ajax(this.baseurl.getAll());
    }
    //下载模板
    downloadTemplate(){
        return this.ajax(this.baseurl.downloadTemplate());
    }
    //批量新增
    batchAdd(){
        return this.ajax(this.baseurl.batchAdd());
    }
    //导入接口
    imports(){
        return this.ajax(this.baseurl.imports());
    }
}
export default base;









