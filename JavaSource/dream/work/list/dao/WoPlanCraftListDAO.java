package dream.work.list.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.dto.WoPlanCraftListDTO;

/**
 * 작업계획목록 - 작업자 목록 dao
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface WoPlanCraftListDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param woPlanCommonDTO
     * @param woPlanCraftListDTO
     * @param loginUser
     * @return List
     */
    public List findCraftList(WoPlanCommonDTO woPlanCommonDTO, WoPlanCraftListDTO woPlanCraftListDTO, User loginUser);
    
    /**
     * delete
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteCraftList(String id, String compNo);
    
    public String findTotalCount(WoPlanCommonDTO woPlanCommonDTO, WoPlanCraftListDTO woPlanCraftListDTO, User user) throws Exception;

}