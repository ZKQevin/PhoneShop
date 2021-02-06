package team.gfr.phone.service;

import team.gfr.phone.Dto.UpdateData;
import team.gfr.phone.entity.TbUser;
import team.gfr.phone.result.ResponseData;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * (TbUser)表服务接口
 *
 * @author makejava
 * @since 2021-01-05 01:22:58
 */
public interface TbUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TbUser queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<TbUser> queryAllByLimit(Integer offset, Integer limit);

    /**
     * 新增数据
     *
     * @param tbUser 实例对象
     * @return 实例对象
     */
    Boolean insert(TbUser tbUser);

    /**
     * 修改数据
     *
     * @param tbUser 实例对象
     * @return 实例对象
     */
    TbUser update(TbUser tbUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 登录
     * @param username
     * @param password
     * @param code
     * @param request
     * @return
     */
    ResponseData login(String username, String password, String code, HttpServletRequest request);

    /**
     * 用户总共数
     * @return
     */
    Long usercount();

    Boolean updateuser(UpdateData updateData);
}