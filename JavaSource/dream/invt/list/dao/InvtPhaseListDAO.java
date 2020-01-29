package dream.invt.list.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.invt.list.dto.InvtCommonDTO;



/**
 * ¸ñ·Ï dao
 * @author  kim21017
 * @version $Id: InvtPhaseListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface InvtPhaseListDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: InvtPhaseListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtCommonDTO
     * @param invtPhaseListDTO
     * @return List
     */
    public List findList(InvtCommonDTO invtCommonDTO, User user);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: InvtPhaseListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRow
     * @param user 
     * @param deleteRowsExt 
     * @return
     */
    public int deleteList(String deleteRow, User user);
    
    public String findTotalCount(InvtCommonDTO invtCommonDTO, User user) throws Exception;

    public int insertPhase(InvtCommonDTO invtCommonDTO, User user) throws Exception;

    public String validPhase(InvtCommonDTO invtCommonDTO, User user) throws Exception;
}