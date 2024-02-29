package cn.allbs.admin.service.test.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.allbs.admin.dao.test.TestFirDao;
import cn.allbs.admin.entity.test.TestFirEntity;
import cn.allbs.admin.dto.test.TestFirDTO;
import cn.allbs.admin.vo.test.TestFirVO;
import cn.allbs.admin.service.test.TestFirService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * (test_fir)表服务实现类
 *
 * @author chenqi
 * @since 2024-02-29 10:15:55
 */
@Service("testFirService")
@RequiredArgsConstructor
public class TestFirServiceImpl extends ServiceImpl<TestFirDao, TestFirEntity> implements TestFirService {

    private final TestFirDao testFirDao;

    /**
     * 分页查询所有数据
     *
     * @param page       分页参数
     * @param testFirDTO 查询参数
     * @return 指定页码和条数的数据
     */
    @Override
    public IPage<TestFirVO> queryPage(Page<TestFirDTO> page, TestFirDTO testFirDTO) {
        return this.testFirDao.queryList(page, testFirDTO);
    }

    /**
     * 查询所有数据
     *
     * @param testFirDTO 查询参数
     * @return 所有数据
     */
    @Override
    public List<TestFirVO> queryList(TestFirDTO testFirDTO) {
        return this.testFirDao.queryList(testFirDTO);
    }
}
