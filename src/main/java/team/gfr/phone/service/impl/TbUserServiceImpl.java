package team.gfr.phone.service.impl;

import org.springframework.stereotype.Service;
import team.gfr.phone.Dto.UpdateData;
import team.gfr.phone.dao.TbUserDao;
import team.gfr.phone.entity.TbUser;
import team.gfr.phone.result.ResponseData;
import team.gfr.phone.service.TbUserService;
import team.gfr.phone.util.Md5Utils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * (TbUser)表服务实现类
 *
 * @author makejava
 * @since 2021-01-05 01:22:58
 */
@Service("tbUserService")
public class TbUserServiceImpl implements TbUserService {
    @Resource
    private TbUserDao tbUserDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TbUser queryById(Integer id) {
        return this.tbUserDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<TbUser> queryAllByLimit(Integer offset, Integer limit) {
        //判断offset 和limit 是否为null（写业务）
        if(offset !=null && limit !=null){
            offset=(offset-1)*limit;
        }else{
            offset = 0;
            limit = 10;
        }
        return tbUserDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增用户，密码并进行加密
     *
     * @param tbUser 实例对象
     * @return 实例对象
     */
    @Override
    public Boolean insert(TbUser tbUser) {
        TbUser newtbUser = tbUserDao.queryByName(tbUser.getUsername());
        if(newtbUser != null){
            return false;
        }else {
            tbUser.setPassword(Md5Utils.md(tbUser.getPassword(),tbUser.getUsername()));
            return tbUserDao.insert(tbUser)>0;
        }
    }

    /**
     * 修改数据
     *
     * @param tbUser 实例对象
     * @return 实例对象
     */
    @Override
    public TbUser update(TbUser tbUser) {
        this.tbUserDao.update(tbUser);
        return this.queryById(tbUser.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.tbUserDao.deleteById(id) > 0;
    }

    /**
     * 登录
     * @param username
     * @param password
     * @param code
     * @param request
     * @return
     */
    @Override
    public ResponseData login(String username, String password, String code, HttpServletRequest request) {
        //先验证验证码是否正确
        String code1= (String) request.getSession().getAttribute("code");
        //忽略大小写
        if(!code1.equalsIgnoreCase(code)){
            return new ResponseData("401", "验证码错误！");
        }
        //再判断用户是否存在？利用用户名去数据库判断，不能用密码去判断
        TbUser tbUser = tbUserDao.queryByName(username);
        //在利用MD5加密方法来对密码进行验证
        if(tbUser!=null&& Md5Utils.md(password,username).equals(tbUser.getPassword())){
            request.getSession().setAttribute("user",tbUser);
            return new ResponseData("200", "登陆成功！");
        }else {
            return new ResponseData("402", "用户名或密码错误！");
        }
    }

    /**
     * 用户总共数
     * @return
     */
    @Override
    public Long usercount() {
        return tbUserDao.usercount();
    }

    /**
     * 实现更新
     * @param updateData
     * @return
     */
    @Override
    public Boolean updateuser(UpdateData updateData) {
        TbUser tbUser = tbUserDao.queryById(updateData.getId());
        updateData.setValue(Md5Utils.md(updateData.getValue(),tbUser.getUsername()));
        return tbUserDao.updateuser(updateData);
    }
}