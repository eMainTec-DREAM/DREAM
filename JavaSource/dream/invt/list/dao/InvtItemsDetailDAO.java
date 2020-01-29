package dream.invt.list.dao;

import common.bean.User;
import dream.invt.list.dto.InvtCommonDTO;
import dream.invt.list.dto.InvtItemsDetailDTO;

/**
 * »ó¼¼ dao
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface InvtItemsDetailDAO
{
    /**
     * detail find
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param invtItemsListDTO
     * @param invtCommonDTO
     * @return
     */
    public InvtItemsDetailDTO findDetail(InvtCommonDTO invtCommonDTO, User user);

    /**
     * detail update
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param invtItemsDetailDTO
     * @param invtCommonDTO
     * @return
     */
    public int updateDetail(InvtItemsDetailDTO invtItemsDetailDTO, InvtCommonDTO invtCommonDTO, User user);
    
    /**
     * detail insert
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param invtItemsDetailDTO
     * @param invtCommonDTO
     * @return
     */
    public int insertDetail(InvtItemsDetailDTO invtItemsDetailDTO, InvtCommonDTO invtCommonDTO, User user);
    
    public String copyDetail(String oldInvtId, String newInvtId, String oldKeyId, String newKeyId, User user) throws Exception;

}