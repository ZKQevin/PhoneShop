package team.gfr.phone.controller;

import org.springframework.web.bind.annotation.*;
import team.gfr.phone.Dto.UpdateData;
import team.gfr.phone.entity.TbUser;
import team.gfr.phone.result.ExceptionMsg;
import team.gfr.phone.result.ResponseData;
import team.gfr.phone.service.TbUserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TbUser)表控制层
 *
 * @author makejava
 * @since 2021-01-05 01:22:59
 */
@RestController
@RequestMapping("tbUser")
public class TbUserController {
    /**
     * 服务对象
     */
    @Resource
    private TbUserService tbUserService;

    /*
     * 接口描述：通过主键查询
     * 请求方式：get
     * Url地址：http://localhost:8080/tbUser/queryById?id=
     * 参数：id    主键
     * 返回值（封装）：{ExceptionMsg.SUCCESS,tbPhone}
     * */
    @GetMapping("queryById")
    public ResponseData queryById(Integer id) {
        TbUser tbPhone = tbUserService.queryById(id);
        return new ResponseData(ExceptionMsg.SUCCESS,tbPhone);
    }

    /*
     * 接口描述：列表展示页
     * 请求方式：get
     * Url地址：http://localhost:8080/tbUser/queryByLimit/1/10
     * 参数：page 开始页    limit 查询条数
     * 返回值（封装）：{"0","操作成功",tbPhones,phonecount}
     * */
    @GetMapping("queryAllByLimit")
    public ResponseData queryAllByLimit(Integer page,Integer limit){
        List<TbUser> tbPhones = tbUserService.queryAllByLimit(page, limit);
        Long usercount = tbUserService.usercount();
        return new ResponseData("0","操作成功",tbPhones,usercount);
    }

    /*
     * 接口描述：新增用户
     * 请求方式：post
     * Url地址：http://localhost:8080/tbUser/upuser
     * 参数：用户对象
     * 返回值（封装）：{ExceptionMsg.SUCCESS/ExceptionMsg.FAILED}
     * */
    @PostMapping("upuser")
    public ResponseData upuser(@RequestBody TbUser tbUser){
        Boolean status = tbUserService.insert(tbUser);
        if(status){
            return new ResponseData(ExceptionMsg.SUCCESS);
        }
        else{
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }

    /*
     * 接口描述：通过主键删除用户
     * 请求方式：Delete
     * Url地址：http://localhost:8080/tbUser/deleteById
     * 参数：手机对象
     * 返回值（封装）：{ExceptionMsg.SUCCESS/ExceptionMsg.FAILED}
     * */
    @DeleteMapping("deleteById/{id}")
    public ResponseData deleteById(@PathVariable Integer id){
        boolean status = tbUserService.deleteById(id);
        if (status) {
            return new ResponseData(ExceptionMsg.SUCCESS);
        }
        else{
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }

    /* 接口描述：通过主键修改指定信息
     * Url地址：http://localhost:8080/tbUser/updateuser
     * 请求方式：put
     * 参数：{"filed":"password","value":"admin","id":"2"}
     * update tb_phone set password = "admin" where id = 2
     * 返回值（封装）：{"code":200,"msg":"操作成功"},{"code":500,"msg":"操作失败"}
     * */
    @PatchMapping("updateuser")
    public ResponseData updateuser(@RequestBody UpdateData updateData){
        Boolean status = tbUserService.updateuser(updateData);
        if(status){
            return new ResponseData(ExceptionMsg.SUCCESS);
        }
        else{
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }
}