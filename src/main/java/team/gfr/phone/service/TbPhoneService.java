package team.gfr.phone.service;

import org.springframework.web.multipart.MultipartFile;
import team.gfr.phone.Dto.UpdateData;
import team.gfr.phone.entity.TbPhone;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * (TbPhone)表服务接口
 *
 * @author makejava
 * @since 2021-01-05 01:22:53
 */
public interface TbPhoneService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TbPhone queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<TbPhone> queryAllByLimit(Integer offset, Integer limit);

    /**
     * 新增数据
     *
     * @param tbPhone 实例对象
     * @return 实例对象
     */
    Boolean insert(TbPhone tbPhone);

    /**
     * 修改数据
     *
     * @param tbPhone 实例对象
     * @return 实例对象
     */
    TbPhone update(TbPhone tbPhone);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 统计手机查询的条数
     * @return
     */
    Long phonecount();

    /**
     * 模糊查询
     * @param value
     */
    List<TbPhone> querylike(String value);

    /**
     * 通过id进行更新指定区域
     * @param updateData
     * @return
     */
    Boolean updatephone(UpdateData updateData);

    /**
     * 图片上传
     * @param file
     * @param request
     * @return
     */
    String upfile(MultipartFile file, HttpServletRequest request);
}