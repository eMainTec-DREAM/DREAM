package dream.part.rep.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.part.rep.dto.MaPtRepCommonDTO;

/**
 * 何前荐府 - 格废 dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaPtRepListDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRepCommonDTO
     * @return List
     */
    public List findList(MaPtRepCommonDTO maPtRepCommonDTO, User user);
    
    /**
     * delete
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteList(String compNo, String ptRepairListId);
    
    public String findTotalCount(MaPtRepCommonDTO maPtRepCommonDTO,User user);
}