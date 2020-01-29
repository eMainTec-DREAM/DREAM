package dream.mgr.usrdata.service;

import java.util.List;

import common.bean.User;
import dream.mgr.usrdata.dto.McDataSelectCommonDTO;


/**
 * 사용자데이터 - 목록 service
 * @author  kim21017
 * @version $Id: McDataSelectListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface McDataSelectPopupService
{     
    /**
     *  grid find
     * @author  jung7126
     * @version $Id: McDataSelectListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param mcDataSelectCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findList(McDataSelectCommonDTO mcDataSelectCommonDTO, User loginUser) throws Exception;

	/**
	 * Write Log for Script Execution 
	 * @param mcDataSelectCommonDTO
	 * @param errMsg
	 */
	public void makeLogForScript(McDataSelectCommonDTO mcDataSelectCommonDTO, String errMsg, User loginUser);   

	/**
	 * total count
	 * @author  js.lee
	 * @param mcDataSelectCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(McDataSelectCommonDTO mcDataSelectCommonDTO, User user) throws Exception;

    public McDataSelectCommonDTO findScript(McDataSelectCommonDTO mcDataSelectCommonDTO, User user) throws Exception;

}
