package com.jnetdata.simple.upload.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by WFJ on 2019/4/18.
 */
public interface UpLoadDataService {
    String UpLoadData(MultipartFile file, int type);
}
