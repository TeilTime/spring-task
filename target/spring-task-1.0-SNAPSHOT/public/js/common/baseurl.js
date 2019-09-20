/**
 * Created by Admin on 2019/2/27.
 */



class  baseurl {
    /**
     *
     * @param baseUrl url地址
     * @param data  向后台传递的参数
     * @param id 触发事件获取的id（可传“”）
     */
    constructor(baseUrl,data,id){
        this.baseUrl = baseUrl;
        this.data = data;
        this.id = id;
    }

    /**
     * 新增方法
     * @param url
     * @returns {{}}
     */
    add(){
        var para = {};
        para.url = this.baseUrl;
        para.type = "post";
        para.async = true;
        para.data = this.data;
        return para;
    }
    /**
     * 更新方法
     * @param url
     * @param id
     * @returns {{}}
     */
    updata(){
        var para = {};
        para.url = this.baseUrl+"/"+this.id;
        para.type = "put";
        para.data = this.data;
        return para;
    }

    /**
     * 根据id查询
     * @param url
     * @param id
     * @returns {{}}
     */
    view(){
        var para = {};
        para.url = this.baseUrl+"/"+this.id;
        para.type = "get";
        para.data = this.data;
        return para;
    }

    /**
     * 删除方法
     * @param url
     * @param id
     * @returns {{}}
     */
    delete(){
        var para = {};
        para.url = this.baseUrl+"/"+this.id;
        para.type = "delete";
        para.data = this.data;
        return para;
    }


    deleteByIds(){
        var para = {};
        para.url = this.baseUrl+"/"+"delByIds",
        para.type = "get";
        para.data = this.data;
        return para;
    }

    /**
     * 列表查询
     * @returns {{}}
     */
    list(){
        var para = {};
        para.url = this.baseUrl+"/"+"list";
        para.type = "post";
        para.data = this.data;
        return para;
    }

    /**
     * 查询所有数据
     * @returns {{}}
     */
    getAll(){
        var para = {};
        para.url = this.baseUrl+"/"+"allList";
        para.type = "post";
        para.data = this.data;
        return para;
    }

    /**
     * 下载模板
     * @returns {{}}
     */
    downloadTemplate(){
        var para = {};
        para.url = this.baseUrl+"/"+"downloadTemplate";
        para.type = "get";
        para.data = this.data;
        return para;
    }

    /**
     * 批量新增
     * @returns {{}}
     */
    batchAdd(){
        var para = {};
        para.url = this.baseUrl+"/"+"downloadTemplate";
        para.type = "post";
        para.data = this.data;
        return para;
    }

    /**
     * 导入
     * @returns {{}}
     */
    imports(){
        var para = {};
        para.url = this.baseUrl+"/"+"imports";
        para.type = "post";
        para.data = this.data;
        return para;

    }
}

export default baseurl;
