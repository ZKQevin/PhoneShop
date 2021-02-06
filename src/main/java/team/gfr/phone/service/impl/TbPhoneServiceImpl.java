package team.gfr.phone.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import team.gfr.phone.Dto.UpdateData;
import team.gfr.phone.dao.TbPhoneDao;
import team.gfr.phone.entity.TbPhone;
import team.gfr.phone.service.TbPhoneService;
import team.gfr.phone.util.UpUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * (TbPhone)表服务实现类
 *
 * @author makejava
 * @since 2021-01-05 01:22:54
 */
@Service("tbPhoneService")
public class TbPhoneServiceImpl implements TbPhoneService {
    @Resource
    private TbPhoneDao tbPhoneDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TbPhone queryById(Integer id) {
        return this.tbPhoneDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<TbPhone> queryAllByLimit(Integer offset, Integer limit) {
        //判断offset 和limit 是否为null（写业务）
        if(offset !=null && limit !=null){
            offset=(offset-1)*limit;
        }else{
            offset = 0;
            limit = 10;
        }
        return tbPhoneDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tbPhone 实例对象
     * @return 实例对象
     */
    @Override
    public Boolean insert(TbPhone tbPhone) {
        return this.tbPhoneDao.insert(tbPhone)>0;
    }

    /**
     * 修改数据
     *
     * @param tbPhone 实例对象
     * @return 实例对象
     */
    @Override
    public TbPhone update(TbPhone tbPhone) {
        this.tbPhoneDao.update(tbPhone);
        return this.queryById(tbPhone.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.tbPhoneDao.deleteById(id) > 0;
    }

    /**
     * 统计手机的总量
     * @return
     */
    @Override
    public Long phonecount() {
        return tbPhoneDao.phonecount();
    }

    /**
     * 模糊查询
     * @param value
     * @return
     */
    @Override
    public List<TbPhone> querylike(String value) {
        return tbPhoneDao.querylike(value);
    }

    /**
     * 通过id进行更新指定区域
     * @param updateData
     * @return
     */
    @Override
    public Boolean updatephone(UpdateData updateData) {
        return tbPhoneDao.updatephone(updateData);
    }

    /**
     * 图片上传
     * @param file
     * @param request
     * @return
     */
    @Override
    public String upfile(MultipartFile file, HttpServletRequest request) {
        //先判断文件是否为空
        if(file.getSize() ==0){
            return null;
        }else{
            return UpUtils.upfile(file,request);
        }
    }
}