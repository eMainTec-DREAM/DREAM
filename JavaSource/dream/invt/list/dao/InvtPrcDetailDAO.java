package dream.invt.list.dao;

import common.bean.User;
import dream.invt.list.dto.InvtCommonDTO;
import dream.invt.list.dto.InvtPrcDetailDTO;
import dream.pers.appreq.dto.AppReqDetailDTO;

/**
 * 상세 dao
 * 
 * @author kim21017
 * @version $Id: InvtPrcDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 */
public interface InvtPrcDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: InvtPrcDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtCommonDTO
     * @return
     */
    public InvtPrcDetailDTO findDetail(InvtCommonDTO invtCommonDTO, User use);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: InvtPrcDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtPrcDetailDTO
     * @return
     */
    public int insertDetail(InvtPrcDetailDTO invtPrcDetailDTO, User user);

    /**
     * detail update
     * @author kim21017
     * @version $Id: InvtPrcDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtPrcDetailDTO
     * @return
     */
    public int updateDetail(InvtPrcDetailDTO invtPrcDetailDTO, User user);

	/**
	 * Insert Phase
	 * @param invtPrcDetailDTO
	 * @param user 
	 */
	public void insertPhase(InvtPrcDetailDTO invtPrcDetailDTO, User user);
	/**
	 * 절차 중복 검사
	 * @param invtCommonDTO
	 * @param invtPrcDetailDTO
	 * @param use
	 * @return
	 */
	public String checkPrc(InvtCommonDTO invtCommonDTO,InvtPrcDetailDTO invtPrcDetailDTO, User use);
	/**
	 * Delete Process
	 * @param invtPrcDetailDTO
	 * @param user
	 */
	public void deletePhase(InvtPrcDetailDTO invtPrcDetailDTO, User user);

    public int setStatus(AppReqDetailDTO appReqDetailDTO, User user);
    
}