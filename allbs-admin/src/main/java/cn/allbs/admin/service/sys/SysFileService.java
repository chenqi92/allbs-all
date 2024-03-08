package cn.allbs.admin.service.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.allbs.admin.entity.sys.SysFileEntity;
import cn.allbs.admin.dto.sys.SysFileDTO;
import cn.allbs.admin.vo.sys.SysFileVO;

import java.util.List;

/**
 * 文件管理表(sys_file)表服务接口
 *
 * @author chenqi
 * @since 2024-03-08 17:39:06
 */
public interface SysFileService extends IService<SysFileEntity> {

    /**
     * 分页查询所有数据
     *
     * @param page       分页参数
     * @param sysFileDTO 查询参数
     * @return IPage<SysFileVO> sysFileDTO
     */
    IPage<SysFileVO> queryPage(Page<SysFileDTO> page, SysFileDTO sysFileDTO);

    /**
     * 查询所有数据
     *
     * @param sysFileDTO 查询参数
     * @return List<SysFileVO>
     */
    List<SysFileVO> queryList(SysFileDTO sysFileDTO);
}
