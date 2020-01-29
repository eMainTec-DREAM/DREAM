package dream.invt.list.dao;

import common.bean.User;
import dream.invt.list.dto.InvtCommonDTO;
import dream.invt.list.dto.InvtPhaseDetailDTO;

/**
 * »ó¼¼ dao
 * @author  kim21017
 * @version $Id: InvtPhaseDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface InvtPhaseDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: InvtPhaseDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtPhaseListDTO
     * @param invtCommonDTO
     * @return
     */
    public InvtPhaseDetailDTO findDetail(InvtCommonDTO invtCommonDTO, User user);

    /**
     * detail update
     * @author kim21017
     * @version $Id: InvtPhaseDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtPhaseDetailDTO
     * @param invtCommonDTO
     * @return
     */
    public int updateDetail(InvtPhaseDetailDTO invtPhaseDetailDTO, InvtCommonDTO invtCommonDTO, User user);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: InvtPhaseDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtPhaseDetailDTO
     * @param invtCommonDTO
     * @return
     */
    public int insertDetail(InvtPhaseDetailDTO invtPhaseDetailDTO, InvtCommonDTO invtCommonDTO);

	public int findVal(InvtPhaseDetailDTO invtPhaseDetailDTO, InvtCommonDTO invtCommonDTO);
	
    public String copyDetail(String oldInvtId, String newInvtId, String oldKeyId, String newKeyId, User user) throws Exception;
}