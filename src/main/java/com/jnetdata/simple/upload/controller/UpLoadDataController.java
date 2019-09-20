package com.jnetdata.simple.upload.controller;

import com.jnetdata.simple.upload.service.UpLoadDataService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thenicesys.web.BaseController;
import org.thenicesys.web.JsonResult;

/**
 * Created by WFJ on 2019/4/18.
 */

@Controller
@Slf4j
@RequestMapping("/simple/upLoadData")
public class UpLoadDataController extends BaseController {
    @Autowired
    UpLoadDataService service;



    @ApiOperation(value = "上传文件", notes="上传文件")
    @PostMapping("/upload")
    @ResponseBody
    public JsonResult uploadExcel(MultipartFile file, @RequestParam("type") int type)throws Exception {
        return JsonResult.renderSuccess((Object)getService().UpLoadData(file,type));
    }


    private UpLoadDataService getService() {
        return this.service;
    }

}
