package dream.rcm.pmtask.service;

import java.util.List;

import common.bean.User;
import dream.rcm.pmtask.dto.RcmPmtaskCommonDTO;
import dream.rcm.pmtask.dto.RcmPmtaskMapListDTO;


/**
 * 답변 목록
 * @author  kim21017
 * @version $Id: RcmPmtaskMapListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface RcmPmtaskMapListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: RcmPmtaskMapListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmPmtaskCommonDTO
     * @param rcmPmtaskMapListDTO
     * @throws Exception
     */
    public List findList(RcmPmtaskCommonDTO rcmPmtaskCommonDTO, RcmPmtaskMapListDTO rcmPmtaskMapListDTO);
	/**
	 * Find Question List
	 * @param rcmPmtaskCommonDTO
	 * @param rcmPmtaskMapListDTO
	 * @return
	 */
	public List findQuestionList(RcmPmtaskCommonDTO rcmPmtaskCommonDTO, RcmPmtaskMapListDTO rcmPmtaskMapListDTO);

	/**
	 * Insert Question 
	 * @param rcmPmtaskCommonDTO
	 * @param rcmPmtaskMapListDTO
	 */
	public void insertMapList(RcmPmtaskCommonDTO rcmPmtaskCommonDTO, RcmPmtaskMapListDTO rcmPmtaskMapListDTO);
	/**
	 * Delete all List
	 * @param rcmPmtaskCommonDTO
	 * @param rcmPmtaskMapListDTO
	 */
	public void deleteList(RcmPmtaskCommonDTO rcmPmtaskCommonDTO, RcmPmtaskMapListDTO rcmPmtaskMapListDTO);
	public String findTotalCount(RcmPmtaskCommonDTO rcmPmtaskCommonDTO, RcmPmtaskMapListDTO rcmPmtaskMapListDTO, User user);

}
