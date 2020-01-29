package dream.work.list.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.dto.WoPlanWoLetListDTO;

/**
 * 작업계획목록 - 안전작업 목록 dao
 * @author  syyang
 * @version $Id$
 * @since   1.0
 */
public interface WoPlanWoLetListDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * grid find
     * @author  syyang
     * @version $Id$
     * @since   1.0
     * 
     * @param woPlanCommonDTO
     * @param woPlanWoLetListDTO
     * @param loginUser
     * @return List
     */
    public List findWoLetList(WoPlanCommonDTO woPlanCommonDTO, WoPlanWoLetListDTO woPlanWoLetListDTO, User loginUser);
    
    /**
     * delete
     * @author syyang
     * @version $Id$
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteWoLetList(String id, String compNo);
    
    public String findTotalCount(WoPlanCommonDTO woPlanCommonDTO, WoPlanWoLetListDTO woPlanWoLetListDTO, User user) throws Exception;

}