package dream.rcm.pmtask.dao;

import java.util.List;

import common.bean.User;
import dream.rcm.pmtask.dto.RcmPmtaskCndtListDTO;
import dream.rcm.pmtask.dto.RcmPmtaskCommonDTO;


/**
 * ¸ñ·Ï dao
 * @author  kim21017
 * @version $Id: RcmPmtaskCndtListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface RcmPmtaskCndtListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: RcmPmtaskCndtListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmPmtaskCommonDTO
     * @param rcmPmtaskCndtListDTO
     * @return List
     */
    public List findList(RcmPmtaskCommonDTO rcmPmtaskCommonDTO, RcmPmtaskCndtListDTO rcmPmtaskCndtListDTO);

	/**
	 * Delete List
	 * @param string
	 * @return
	 */
	public int deleteList(String string);

	public String findTotalCount(RcmPmtaskCommonDTO rcmPmtaskCommonDTO, RcmPmtaskCndtListDTO rcmPmtaskCndtListDTO,
			User user);
}