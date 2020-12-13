package com.qy23.sm.web;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.qy23.sm.http.AxiosResuit;
import com.qy23.sm.http.AxiosStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @ClassName UploadController
 * @Author 刘伟
 * @Date 2020/10/21 14:13
 * @Description
 * @Version 1.0
 **/
@RestController
public class UploadController {


    @PostMapping("upload")
    public AxiosResuit upload(@RequestPart Part file) throws IOException {

        List<String> strings = new ArrayList<>();
        strings.add("jpg");
        strings.add("png");
        strings.add("jpeg");

        //判断文件类型
        if (!strings.contains(StringUtils.getFilenameExtension(file.getSubmittedFileName()))) {
            return AxiosResuit.error(AxiosStatus.EXT_ERROR);
        }

        //判断文件大小
        if ((file.getSize() / 1024 / 1024 )>=2) {
            return AxiosResuit.error(AxiosStatus.FILE_TOLONG);
        }
        //判断是否是个图片
        if (Objects.isNull(ImageIO.read(file.getInputStream()))){
            return AxiosResuit.error(AxiosStatus.NOT_IMAGE);
        }

        String endpoint = "http://oss-cn-shenzhen.aliyuncs.com";
        String accessKeyId = "LTAI4G2r8rRdJCSnzsZuc8Ld";
        String accessKeySecret = "7Pa30jIzC0Ur3F3kcDWSihfhgwVqnb";
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        //后缀名
        String extension = StringUtils.getFilenameExtension(file.getSubmittedFileName());
        //新的文件名
        String fileName = UUID.randomUUID().toString().replaceAll("-", "");
        //目录打散
        int hashCode = fileName.hashCode();
        String dir1 = Integer.toHexString(hashCode & 0xff);
        String dir2 = Integer.toHexString((hashCode >> 8) & 0xff);
        //新的文件名
        String realPath = dir1 + "/" + dir2 + "/" + fileName + "." + extension;
        //开始上传
        ossClient.putObject("liuwei-dachahu", realPath, file.getInputStream());
        ossClient.shutdown();
        return AxiosResuit.success("http://liuwei-dachahu.oss-cn-shenzhen.aliyuncs.com/" + realPath);
    }
}
