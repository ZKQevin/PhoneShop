package team.gfr.phone.dao;

import org.apache.ibatis.annotations.Param;
import team.gfr.phone.Dto.UpdateData;
import team.gfr.phone.entity.TbPhone;

import java.util.List;

/**
 * (TbPhone)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-05 01:22:50
 */
public interface TbPhoneDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TbPhone queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<TbPhone> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tbPhone 实例对象
     * @return 对象列表
     */
    List<TbPhone> queryAll(TbPhone tbPhone);

    /**
     * 新增数据
     *
     * @param tbPhone 实例对象
     * @return 影响行数
     */
    int insert(TbPhone tbPhone);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<TbPhone> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<TbPhone> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<TbPhone> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<TbPhone> entities);

    /**
     * 修改数据
     *
     * @param tbPhone 实例对象
     * @return 影响行数
     */
    int update(TbPhone tbPhone);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 统计手机的条数
     * @return
     */
    Long phonecount();

    /**
     * 模糊查询
     * @param value
     * @return
     */
    List<TbPhone> querylike(String value);

    /**
     * 通过主键修改指定区域
     * @param updateData
     * @return
     */
    Boolean updatephone(UpdateData updateData);
}