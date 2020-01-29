package dream.invt.list.service;

import java.util.List;

import common.bean.User;
import dream.invt.list.dto.InvtCommonDTO;

/**
 * 구매항목 목록
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface InvtItemsListService
{     
    /**
     *  grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param invtCommonDTO
     * @param invtItemsListDTO
     * @throws Exception
     */
    public List findList(InvtCommonDTO invtCommonDTO, User user);
    /**
     *  delete
     * @author  youngjoo38
     * @version $Id$
     * @param user 
     * @since   1.0
     * 
     * @param strings
     * @param d_id 
     * @throws Exception
     */
    public int deleteList(String[] m_id, User user) throws Exception;

    public String findTotalCount(InvtCommonDTO invtCommonDTO, User user) throws Exception;

}
