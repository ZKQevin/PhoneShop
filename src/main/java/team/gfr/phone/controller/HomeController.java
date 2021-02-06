package team.gfr.phone.controller;

import cn.dsna.util.images.ValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import team.gfr.phone.result.ResponseData;
import team.gfr.phone.service.TbPhoneService;
import team.gfr.phone.service.TbUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("home")
public class HomeController {
    /**
     * 注入服务层
     */
    @Autowired
    private TbPhoneService tbPhoneService;
    @Autowired
    private TbUserService tbUserService;
    @Autowired
    private TbUserController tbUserController;

    /**
     * 验证码
     * @param request
     * @param response
     */
    @GetMapping("code")
    private void getCode(HttpServletRequest request, HttpServletResponse response) {
        /*创建一个验证码   参数分别表示为图片宽度，高度，验证码的文本数量，干扰条数*/
        ValidateCode validateCode = new ValidateCode(120, 40, 4, 50);
        /*获取验证码生成的文本*/
        String code = validateCode.getCode();
        /*将文本放在session中     一个公共的存储空间   存储方式为key-value值*/
        request.getSession().setAttribute("code", code);
        request.getSession().setMaxInactiveInterval(300);//设置过期时间
        try {
            validateCode.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 登录页面
     * @return login
     */
    //登录界面，只是为了去后台获取数据以后返回一个页面
    @GetMapping("login")
    @ResponseBody
    public ResponseData login(String username, String password, String code, HttpServletRequest request) {
        return tbUserService.login(username, password, code, request);
    }

    /**
     * 后台系统页面
     * @return system
     */
    @GetMapping("system")
    public String system() {
        return "system";
    }



    /**
     * 主页
     * @return phone
     */
    @GetMapping("main")
    public String main() {
        return "main";
    }



    /**
     * 手机列表
     * @return phone
     */
    @GetMapping("phone")
    public String phone() {
        return "phone";
    }

    /**
     * 上传手机页面
     * @return upphone
     */
    @GetMapping("upphone")
    public String upphone() {
        return "upphone";
    }

    /**
     * 用户列表
     * @return userlist
     */
    @GetMapping("userlist")
    public String userlist() {
        return "userlist";
    }

    /**
     * 新增用户
     * @return upuser
     */
    @GetMapping("upuser")
    public String upuser() {
        return "upuser";
    }


}
