package dream.rcm.pmtask.dao;

import java.util.List;

import com.fins.org.json.JSONObject;

import common.bean.User;
import dream.rcm.pmtask.dto.RcmPmtaskCommonDTO;
import dream.rcm.pmtask.dto.RcmPmtaskMapListDTO;


/**
 * ¸ñ·Ï dao
 * @author  kim21017
 * @version $Id: RcmPmtaskMapListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface RcmPmtaskMapListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: RcmPmtaskMapListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmPmtaskCommonDTO
     * @param rcmPmtaskMapListDTO
     * @return List
     */
    public List findList(RcmPmtaskCommonDTO rcmPmtaskCommonDTO, RcmPmtaskMapListDTO rcmPmtaskMapListDTO);

	/**
	 * Find Questions
	 * @param rcmPmtaskCommonDTO
	 * @param rcmPmtaskMapListDTO
	 * @return
	 */
	public List findQuestionList(RcmPmtaskCommonDTO rcmPmtaskCommonDTO, RcmPmtaskMapListDTO rcmPmtaskMapListDTO);

	/**
	 * Insert Map Data
	 * @param rcmPmtaskCommonDTO
	 * @param rcmPmtaskMapListDTO
	 * @param json 
	 */
	public void insertMapList(RcmPmtaskCommonDTO rcmPmtaskCommonDTO, RcmPmtaskMapListDTO rcmPmtaskMapListDTO, JSONObject json);

	/**
	 * @param rcmPmtaskCommonDTO
	 * @param rcmPmtaskMapListDTO
	 */
	public void deleteList(RcmPmtaskCommonDTO rcmPmtaskCommonDTO, RcmPmtaskMapListDTO rcmPmtaskMapListDTO);

	public String findTotalCount(RcmPmtaskCommonDTO rcmPmtaskCommonDTO, RcmPmtaskMapListDTO rcmPmtaskMapListDTO, User user);
}