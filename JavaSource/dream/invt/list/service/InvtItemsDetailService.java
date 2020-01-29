package dream.invt.list.service;

import common.bean.User;
import dream.invt.list.dto.InvtCommonDTO;
import dream.invt.list.dto.InvtItemsDetailDTO;

/**
 * detail
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface InvtItemsDetailService
{    
    /**
     * detail find
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param invtItemsListDTO
     * @param invtCommonDTO
     * @param user 
     * @return
     * @throws Exception
     */
    public InvtItemsDetailDTO findDetail(InvtCommonDTO invtCommonDTO, User user)throws Exception;
    /**
     * detail update
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param invtItemsDetailDTO
     * @param invtCommonDTO
     * @param user 
     * @return
     * @throws Exception
     */
    public int updateDetail(InvtItemsDetailDTO invtItemsDetailDTO, InvtCommonDTO invtCommonDTO, User user) throws Exception;
    /**
     * detail insert
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param invtItemsDetailDTO
     * @param invtCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(InvtItemsDetailDTO invtItemsDetailDTO, InvtCommonDTO invtCommonDTO, User user) throws Exception;

    public String copyDetail(String oldInvtId, String newInvtId, String oldKeyId, String newKeyId, User user) throws Exception;
}
