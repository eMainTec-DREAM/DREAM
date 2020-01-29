package dream.part.list.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.part.list.dto.MaPtMstrCommonDTO;

/**
 * 부품마스터 입고이력 - 목록 dao
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface MaPtMstrRecStatListDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtMstrCommonDTO
     * @return List
     */
    public List findPtMstrRecStatList(MaPtMstrCommonDTO maPtMstrCommonDTO,User user);

    public String findTotalCount(MaPtMstrCommonDTO maPtMstrCommonDTO, User user) throws Exception;
}