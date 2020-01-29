package dream.part.iss.wo.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.part.iss.wo.dto.MaPtIssCommonDTO;
import dream.part.list.dto.MaPtMstrCommonDTO;

/**
 * 자재출고확정 - 목록 dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaPtIssListDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtIssCommonDTO
     * @return List
     */
    public List findPtIssList(MaPtIssCommonDTO maPtIssCommonDTO, User user);
    
    public String findTotalCount(MaPtIssCommonDTO maPtIssCommonDTO, User user);
    
    public int deletePtIss(String ptisslistId, User user);

    public int deleteWoParts(String wopartId, User user);

    public int unlinkWoParts(String ptisslistId, User user);
}