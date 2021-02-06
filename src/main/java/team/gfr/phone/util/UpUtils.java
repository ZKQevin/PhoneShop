package team.gfr.phone.util;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 文件上传工具类
 */
public class UpUtils {

    //这个方法使用static修饰，在程序一启动的时候直接创建了，可以直接使用其方法
    public static String upfile(MultipartFile file, HttpServletRequest request) {
        //获取当前文件的名称
        String originalFilename = file.getOriginalFilename();
        //判断当前文件是什么类型
        //真实路径
        String realPath = "";
        //本地路径
        String localPath = "";
        //返回值
        String url="";
        if (originalFilename.endsWith(".mp4")) {
            //获取服务器的路径TomCat(为了将文件存放在服务器中)
            //http:localhost:8080/
            realPath = request.getSession().getServletContext().getRealPath("/video/");
            localPath ="D:\\QianFengPeiXun\\Spring boot\\mphoneShop\\src\\main\\resources\\static\\video\\";
            url="http://localhost:8080/video/"+originalFilename;
        } else if (originalFilename.endsWith(".jpg") || originalFilename.endsWith(".png") || originalFilename.endsWith(".jpeg")) {
            realPath = request.getSession().getServletContext().getRealPath("/img/");
            localPath ="D:\\QianFengPeiXun\\Spring boot\\mphoneShop\\src\\main\\resources\\static\\img\\";
            url="http://localhost:8080/img/"+originalFilename;
        } else if (originalFilename.endsWith(".mp3")) {
            realPath = request.getSession().getServletContext().getRealPath("/audio/");
            localPath ="D:\\QianFengPeiXun\\Spring boot\\mphoneShop\\src\\main\\resources\\static\\audio\\";
            url="http://localhost:8080/audio/"+originalFilename;
        }

        //创建服务器文件对象
        File file1 = new File(realPath);/*此时realPath是一个地址http:localhost:8080/video/*/
        //判定当前文件是否存在，不存在则创建
        if (!file1.exists()) {
            file1.mkdirs();
        }
        File file2 = new File(localPath);
        //判定当前文件是否存在，不存在则创建
        if (!file2.exists()) {
            file2.mkdirs();
        }


        //创建文件输出流
        FileOutputStream fos = null;
        FileOutputStream fos1 = null;
        try {
            //http://localhost:8080/music-web/video/a.mp4
            // true 表示文件追加   如果为false  则文件夹中永远只有一个文件(最后上传的)
            fos = new FileOutputStream(realPath + originalFilename, true);
            fos.write(file.getBytes());
            fos.flush();
            fos1 = new FileOutputStream(localPath + originalFilename, true);
            fos1.write(file.getBytes());
            fos1.flush();
            return url;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
