package dream.invt.list.dao;

import java.io.IOException;
import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.invt.list.dto.InvtCommonDTO;
import dream.invt.list.dto.InvtDetailDTO;
import dream.pers.appreq.dto.AppReqDetailDTO;

/**
 * »ó¼¼ dao
 * 
 * @author kim21017
 * @version $Id: InvtDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 */
public interface InvtDetailDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: InvtDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtCommonDTO
     * @return
     */
    public InvtDetailDTO findDetail(InvtCommonDTO invtCommonDTO, User user);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: InvtDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtDetailDTO
     * @return
     */
    public int insertDetail(InvtDetailDTO invtDetailDTO, User user);

    /**
     * detail update
     * @author kim21017
     * @version $Id: InvtDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtDetailDTO
     * @return
     * @throws IOException 
     */
    public int updateDetail(InvtDetailDTO invtDetailDTO, User user) throws IOException;

	/**
	 * Insert Phase
	 * @param invtDetailDTO
	 * @param user 
	 * @return 
	 */
	public int insertPhase(InvtDetailDTO invtDetailDTO, User user);

	/**
	 * Delete Process
	 * @param invtDetailDTO
	 * @param user
	 */
	public void deletePhase(InvtDetailDTO invtDetailDTO, User user);

    public int setStatus(AppReqDetailDTO appReqDetailDTO, User user);

    public int updateInvtListStatus(AppReqDetailDTO appReqDetailDTO, User user);

    public String getInvtListId(String invtPhaseId, User user);
    
    public String copyDetail(InvtCommonDTO invtCommonDTO, InvtDetailDTO invtDetailDTO, User user)throws Exception;
    
    /**
     * Cancel Invt Status
     * @author js.lee
     * @version $Id: Exp $
     * @since   1.0
     * 
     * @param invtDetailDTO
     * @return
     * @throws IOException 
     */
    public int cancelInvtStatus(InvtDetailDTO invtDetailDTO, User user) throws IOException;
    
    /**
     * Cancel InvtPhase Status
     * @author js.lee
     * @version $Id: Exp $
     * @since   1.0
     * 
     * @param invtDetailDTO
     * @return
     * @throws IOException 
     */
    public int cancelInvtPhaseStatus(InvtDetailDTO invtDetailDTO, User user) throws IOException;
    
    public String checkStatus(String invtListId, User user) throws IOException;
    
    public List findReq(InvtCommonDTO invtCommonDTO, User user) throws IOException;
}