class conslog{

    /**
     * 跳转页面的方法
     * @param url  跳转url
     */
    goToURL(url){
        window.local(url);
    }

    /**
     *数据打印到控制台方法(log)
     * @param data  需要打印的数据
     */
    consoleLog(data) {
        console.log(data);
    }

    /**
     * 数据打印到控制台方法(error)
     * @param data  需要打印的数据
     */
    consoleError(data){
        console.error(data)
    }
}
export default conslog;
