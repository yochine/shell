package me.zrxjava.system.modules.service.impl;

import me.zrxjava.system.modules.service.IFileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author void
 * @create 2021-04-22
 */
@Service
public class LocalFileServiceImpl  implements IFileService {


    @Override
    public String uploadFile(MultipartFile file) throws Exception {
        String originalFilename = file.getOriginalFilename();
        String projectPath = System.getProperty("user.dir");
        String path = projectPath + File.separator + "file" + File.separator + originalFilename;
        file.transferTo(new File(path));
        return path;
    }
}
