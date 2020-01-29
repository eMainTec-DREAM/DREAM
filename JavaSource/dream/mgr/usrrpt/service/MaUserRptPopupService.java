package dream.mgr.usrrpt.service;

import java.util.List;

import common.bean.User;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;


/**
 * 목록 service
 * @author  kim21017
 * @version $Id: MaUserRptListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaUserRptPopupService
{     
    /**
     *  grid find
     * @author  jung7126
     * @version $Id: MaUserRptListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maUserRptCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findList(MaUserRptCommonDTO maUserRptCommonDTO, User loginUser) throws Exception;

	/**
	 * Write Log for Script Execution 
	 * @param maUserRptCommonDTO
	 * @param errMsg
	 */
	public void makeLogForScript(MaUserRptCommonDTO maUserRptCommonDTO, String errMsg, User loginUser);   

}
