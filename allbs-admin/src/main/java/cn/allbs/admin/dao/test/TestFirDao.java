package cn.allbs.admin.dao.test;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.allbs.admin.entity.test.TestFirEntity;
import cn.allbs.admin.dto.test.TestFirDTO;
import cn.allbs.admin.vo.test.TestFirVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (test_fir)表数据库访问层
 *
 * @author chenqi
 * @since 2024-02-29 10:15:55
 */
@Mapper
public interface TestFirDao extends BaseMapper<TestFirEntity> {

    /**
     * 分页查询所有数据
     *
     * @param page       分页参数
     * @param testFirDTO 查询参数
     * @return IPage<TestFirVO>
     */
    IPage<TestFirVO> queryList(Page<TestFirDTO> page, @Param("testFirDTO") TestFirDTO testFirDTO);

    /**
     * 查询所有数据
     *
     * @param testFirDTO 查询参数
     * @return List<TestFirVO>
     */
    List<TestFirVO> queryList(@Param("testFirDTO") TestFirDTO testFirDTO);
}
