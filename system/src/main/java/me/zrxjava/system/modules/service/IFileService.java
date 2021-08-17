package me.zrxjava.system.modules.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author void
 * @create 2021-04-22
 */
public interface IFileService {


    /**
     * 文件上传接口
     *
     * @param file 上传的文件
     * @return 访问地址
     * @throws Exception
     */
    public String uploadFile(MultipartFile file) throws Exception;

}
