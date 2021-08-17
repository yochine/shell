package me.zrxjava.system.modules.controller;

import io.swagger.annotations.ApiOperation;
import me.zrxjava.common.base.ResponseResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author void
 * @create 2021-04-21
 */
@RestController
@RequestMapping("/file")
public class FileController {


    @ApiOperation("上传文件")
    @PreAuthorize("@ps.check('file:upload')")
    @PostMapping("/upload")
    public ResponseResult<String> upload(@RequestParam("file") MultipartFile file){
        return ResponseResult.success(null);
    }

}
