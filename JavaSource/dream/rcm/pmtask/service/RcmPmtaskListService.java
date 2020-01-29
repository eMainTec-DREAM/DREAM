package dream.rcm.pmtask.service;

import java.util.List;

import common.bean.User;
import dream.rcm.pmtask.dto.RcmPmtaskCommonDTO;

/**
 * 목록 service
 * @author  kim21017
 * @version $Id: RcmPmtaskListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface RcmPmtaskListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: RcmPmtaskListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param rcmPmtaskCommonDTO
     * @param user 
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findList(RcmPmtaskCommonDTO rcmPmtaskCommonDTO, User user);

	public String findTotalCount(RcmPmtaskCommonDTO rcmPmtaskCommonDTO, User user);    

}
