package cn.allbs.admin.service.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.allbs.admin.entity.sys.SysDeptEntity;
import cn.allbs.admin.dto.sys.SysDeptDTO;
import cn.allbs.admin.vo.sys.SysDeptVO;

import java.util.List;

/**
 * 部门管理(sys_dept)表服务接口
 *
 * @author chenqi
 * @since 2024-03-08 17:39:04
 */
public interface SysDeptService extends IService<SysDeptEntity> {

    /**
     * 分页查询所有数据
     *
     * @param page       分页参数
     * @param sysDeptDTO 查询参数
     * @return IPage<SysDeptVO> sysDeptDTO
     */
    IPage<SysDeptVO> queryPage(Page<SysDeptDTO> page, SysDeptDTO sysDeptDTO);

    /**
     * 查询所有数据
     *
     * @param sysDeptDTO 查询参数
     * @return List<SysDeptVO>
     */
    List<SysDeptVO> queryList(SysDeptDTO sysDeptDTO);
}
