package team.gfr.phone.controller;

import cn.dsna.util.images.ValidateCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team.gfr.phone.Dto.UpdateData;
import team.gfr.phone.entity.TbPhone;
import team.gfr.phone.result.ExceptionMsg;
import team.gfr.phone.result.ResponseData;
import team.gfr.phone.service.TbPhoneService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * (TbPhone)表控制层
 *
 * @author makejava
 * @since 2021-01-05 01:22:55
 */
@RestController
@RequestMapping("tbPhone")
public class TbPhoneController {
    /**
     * 服务对象
     */
    @Resource
    private TbPhoneService tbPhoneService;

    //验证码(涉及到文件操作IO流)
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

    /*
     * 接口描述：通过主键查询
     * 请求方式：get
     * Url地址：http://localhost:8080/tbPhone/queryById?id=
     * 参数：id    主键
     * 返回值（封装）：{ExceptionMsg.SUCCESS,tbPhone}
     * */
    @GetMapping("queryById")
    public ResponseData queryById(Integer id) {
        TbPhone tbPhone = tbPhoneService.queryById(id);
        return new ResponseData(ExceptionMsg.SUCCESS,tbPhone);
    }

    /*
     * 接口描述：列表展示页
     * 请求方式：get
     * Url地址：http://localhost:8080/tbPhone/queryByLimit/1/10
     * 参数：page 开始页    limit 查询条数
     * 返回值（封装）：{"0","操作成功",tbPhones,phonecount}
     * */
    @GetMapping("queryAllByLimit")
    public ResponseData queryAllByLimit(Integer page,Integer limit){
        List<TbPhone> tbPhones = tbPhoneService.queryAllByLimit(page, limit);
        Long phonecount = tbPhoneService.phonecount();
        return new ResponseData("0","操作成功",tbPhones,phonecount);
    }

    /*
     * 接口描述：模糊查询
     * 请求方式：get
     * Url地址：http://localhost:8080/tbPhone/queryLike
     * 参数：有可能是品牌    类型     价格
     * 返回值（封装）：{"0","操作成功",tbPhones,phonecount}
     * */
    @GetMapping("queryLike")
    public ResponseData queryLike(String value){
        List<TbPhone> querylike = tbPhoneService.querylike(value);
        return new ResponseData(ExceptionMsg.SUCCESS,querylike);
    }

    /*
     * 接口描述：新增手机
     * 请求方式：post
     * Url地址：http://localhost:8080/tbPhone/upphone
     * 参数：手机对象
     * 返回值（封装）：{ExceptionMsg.SUCCESS/ExceptionMsg.FAILED}
     * */
    @PostMapping("upphone")
    public ResponseData upphone(@RequestBody TbPhone tbPhone){
        Boolean status = tbPhoneService.insert(tbPhone);
        if(status){
            return new ResponseData(ExceptionMsg.SUCCESS);
        }
        else{
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }

    /* 接口描述：通过主键修改指定信息
     * Url地址：http://localhost:8080/tbPhone/updatephone
     * 请求方式：put
     * 参数：{"filed":"name","value":"iPhone 18","id":"1"}
     * update tb_phone set name = "iPhone 18" where id = 1
     * 返回值（封装）：{"code":200,"msg":"操作成功"},{"code":500,"msg":"操作失败"}
     * */
    @PatchMapping("updatephone")
    public ResponseData updatephone(@RequestBody UpdateData updateData){
        Boolean status = tbPhoneService.updatephone(updateData);
        if(status){
            return new ResponseData(ExceptionMsg.SUCCESS);
        }
        else{
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }

    /*
     * 接口描述：通过主键删除手机
     * 请求方式：post
     * Url地址：http://localhost:8080/tbPhone/deleteById
     * 参数：手机对象
     * 返回值（封装）：{ExceptionMsg.SUCCESS/ExceptionMsg.FAILED}
     * */
    @DeleteMapping("deleteById/{id}")
    public ResponseData deleteById(@PathVariable Integer id){
        boolean status = tbPhoneService.deleteById(id);
        if (status) {
            return new ResponseData(ExceptionMsg.SUCCESS);
        }
        else{
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }

    /*
     * 接口描述：文件上传
     * 请求方式Post
     * Url:http://localhost:8080/tbPhone/upfile
     * 参数:file   文件类型对象
     * 返回值{"code":"","msg":"","data":"文件路径"}*/
    @PostMapping("upfile")
    public ResponseData upfile(MultipartFile file, HttpServletRequest request){/*MultipartFile用来接收文件对象的，什么都可以传到*/
        String url = tbPhoneService.upfile(file,request);
        if(url!=null){
            return new ResponseData(ExceptionMsg.SUCCESS,url);
        }else{
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }

}