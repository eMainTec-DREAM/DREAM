package dream.rcm.pmtask.service;

import java.util.List;

import common.bean.User;
import dream.rcm.pmtask.dto.RcmPmtaskCndtListDTO;
import dream.rcm.pmtask.dto.RcmPmtaskCommonDTO;


/**
 * 답변 목록
 * @author  kim21017
 * @version $Id: RcmPmtaskCndtListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface RcmPmtaskCndtListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: RcmPmtaskCndtListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmPmtaskCommonDTO
     * @param rcmPmtaskCndtListDTO
     * @throws Exception
     */
    public List findList(RcmPmtaskCommonDTO rcmPmtaskCommonDTO, RcmPmtaskCndtListDTO rcmPmtaskCndtListDTO);

	/**
	 * Delete List
	 * @param deleteRows
	 * @return
	 */
	public int deleteList(String[] deleteRows);

	public String findTotalCount(RcmPmtaskCommonDTO rcmPmtaskCommonDTO, RcmPmtaskCndtListDTO rcmPmtaskCndtListDTO,
			User user);

}
