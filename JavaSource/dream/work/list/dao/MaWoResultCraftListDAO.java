package dream.work.list.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.work.list.dto.MaWoResultCraftListDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * 작업결과 작업자 목록 dao
 * @author  kim21017
 * @version $Id: MaWoResultCraftListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaWoResultCraftListDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaWoResultCraftListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrCommonDTO
     * @param maWoResultCraftListDTO
     * @param loginUser
     * @return List
     */
    public List findCraftList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultCraftListDTO maWoResultCraftListDTO, User loginUser);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaWoResultCraftListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteCraftList(String id, String compNo);
    
    public String findTotalCount(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultCraftListDTO maWoResultCraftListDTO, User user) throws Exception;
}