package dream.invt.list.dao;

import java.util.List;

import common.bean.User;
import dream.invt.list.dto.InvtCommonDTO;

/**
 * ��� dao
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface InvtItemsListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param invtCommonDTO
     * @param invtItemsListDTO
     * @return List
     */
    public List findList(InvtCommonDTO invtCommonDTO, User user);
    
    /**
     * delete
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param deleteRow
     * @param user 
     * @param deleteRowsExt 
     * @return
     */
    public int deleteList(String deleteRow, User user);
    
    public String findTotalCount(InvtCommonDTO invtCommonDTO, User user) throws Exception;

}