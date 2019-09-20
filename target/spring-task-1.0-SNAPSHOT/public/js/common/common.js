/**
 * Created by Admin on 2019/2/26.
 */
/**
 * Created by Admin on 2019/2/26.
 */

class common {
    /**
     *普通ajax提交数据
     * @param url  请求地址
     * @param type  请求类型（post或者get）
     * @param async   （同步false,异步true）
     * @returns {Promise}
     */
    ajax (para) {
                return new Promise((resovle, reject) => {
                    $.ajax({
                        "type": para.type,
                        "async": para.async,
                        "url": para.url,
                        contentType: 'application/json',
                        "data": para.data,
                        dataType: 'json',
                        "success": res => {
                            resovle(res);
                        },
                        "error": err => {
                            reject(err);
                        }
                    })
                })
    }

    /**
     *
     * @param url   提交的url
     * @param layFilter  按钮的    lay-filter  的属性值
     * @param method  提交方式（get或post）
     * @returns {*}
     */
    layuiForm(layFilter){
        var para;
        layui.form.on('submit('+layFilter+')'),function (obj) {
            para.data = obj.field;
        }
        return promise;
    }
}
export default common;



