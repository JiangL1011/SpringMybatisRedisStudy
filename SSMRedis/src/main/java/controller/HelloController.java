package controller;

import bean.User;
import org.apache.commons.io.FileUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * description:
 * author:  JiangL
 * company:
 * date:    10/17/2018
 * version: v1.0
 */
@Controller
public class HelloController {
    @RequestMapping(value = "/hello1", name = "h1")
    public String hello1(WebRequest request) {
        System.out.println("Hello World!\t1\t" + request);
        request.setAttribute("name", "JiangL", WebRequest.SCOPE_SESSION);
        return "hello";
    }

    @RequestMapping(value = "/hello2", name = "h2")
    public String hello2(HttpServletRequest request) {
        System.out.println("Hello World!\t2\t" + request);
        return "forward:/hello1";
//        return "redirect:/hello1";
    }

    @RequestMapping("param")
    @ResponseBody
    public String param(@RequestParam(name = "name", defaultValue = "jiangling") String name) {
        return name;
    }

    @RequestMapping("hello")
    public String hello(Model model) {
        model.addAttribute("name", "JiangLing");
        return "hello";
    }

    @RequestMapping("sid")
    @ResponseBody
    public String sid(@CookieValue(value = "JSESSIONID", defaultValue = "no session id") String sessionId) {
        return sessionId;
    }

    @RequestMapping("getSession")
    @ResponseBody
    public String getSession(@SessionAttribute(value = "name") String name) {
        return name;
    }

    @RequestMapping("user")
    @ResponseBody
    public Object getUser(@Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            return errors.getAllErrors();
        } else {
            return user;
        }
    }

    @RequestMapping("upload")
    @ResponseBody
    public String uploadFile(@RequestParam MultipartFile file) {
        File tempDir = new File("C:\\Users\\JiangL\\Desktop\\New folder");
        File[] fileArr = tempDir.listFiles();
        return (fileArr != null && fileArr.length > 0) ?
                (fileArr[0].getName() + " - " + file.getOriginalFilename()) : "empty";
    }

    @RequestMapping("download")
    public ResponseEntity<byte[]> download(@RequestHeader("User-Agent") String userAgent) throws IOException {
        File file = new File("C:\\Users\\JiangL\\Desktop\\蒋领-Java开发.doc");
        ResponseEntity.BodyBuilder builder = ResponseEntity.ok();
        builder.contentLength(file.length());
        builder.contentType(MediaType.APPLICATION_OCTET_STREAM);
        String fileName = URLEncoder.encode(file.getName(), "UTF-8");
        if (userAgent.indexOf("MSIE") > 0) {
            // IE只需要用UTF-8对URL编码即可
            builder.header("Content-Disposition", "attachment;filename=" + fileName);
        } else {
            // 火狐谷歌等浏览器需要说明编码的字符集，注意filename后的*号以及UTF-8后的两个单引号
            builder.header("Content-disposition", "attachment;filename*=UTF-8''" + fileName);
        }
        return builder.body(FileUtils.readFileToByteArray(file));
    }

    @RequestMapping("interceptor")
    @ResponseBody
    public void interceptorTest() {
        System.out.println("interceptorTest");
    }

}

