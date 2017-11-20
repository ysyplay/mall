package com.mmall.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by runa on 2017/11/20.
 */
public interface IFileService {
    String upload(MultipartFile file, String path);
}
