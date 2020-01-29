package dream.part.stk.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.part.stk.dto.PartStkCurrentDTO;

/**
 *  ¸ñ·Ï dao
 * @author  euna0207
 * @version $Id$
 * @since   1.0
 */
public interface PartStkCurrentDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * grid find
     * @author  euna0207
     * @version $Id$
     * @since   1.0
     * 
     * @param maMyInfoCommonDTO
     * @param user 
     * @return List
     */
    public List findPtStckList(PartStkCurrentDTO partStkCurrentDTO, User loginUser);
    
    public String findTotalCount(PartStkCurrentDTO partStkCurrentDTO, User user) throws Exception;

    public String getColums(PartStkCurrentDTO partStkCurrentDTO, User user);
    
    public String getTables(PartStkCurrentDTO partStkCurrentDTO, User user);
    
    public String getOrderBy(PartStkCurrentDTO partStkCurrentDTO, User user);
    
    public String getWhere(PartStkCurrentDTO partStkCurrentDTO, User user);
}