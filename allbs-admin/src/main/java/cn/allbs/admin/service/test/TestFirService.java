package cn.allbs.admin.service.test;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.allbs.admin.entity.test.TestFirEntity;
import cn.allbs.admin.dto.test.TestFirDTO;
import cn.allbs.admin.vo.test.TestFirVO;

import java.util.List;

/**
 * (test_fir)表服务接口
 *
 * @author chenqi
 * @since 2024-02-29 10:15:55
 */
public interface TestFirService extends IService<TestFirEntity> {

    /**
     * 分页查询所有数据
     *
     * @param page       分页参数
     * @param testFirDTO 查询参数
     * @return IPage<TestFirVO> testFirDTO
     */
    IPage<TestFirVO> queryPage(Page<TestFirDTO> page, TestFirDTO testFirDTO);

    /**
     * 查询所有数据
     *
     * @param testFirDTO 查询参数
     * @return List<TestFirVO>
     */
    List<TestFirVO> queryList(TestFirDTO testFirDTO);
}
