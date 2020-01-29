package dream.mgr.usrdata.dao;

import java.util.List;

import common.bean.User;
import common.util.QueryBuffer;
import dream.mgr.usrdata.dto.McDataSelectCommonDTO;
import dream.mgr.usrdata.dto.McDataSelectDetailDTO;


/**
 * 메뉴 - 목록 dao
 * @author  kim21017
 * @version $Id: McDataSelectListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface McDataSelectPopupDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: McDataSelectListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param mcDataSelectCommonDTO
     * @return List
     */
    public List findList(McDataSelectCommonDTO mcDataSelectCommonDTO, User loginUser);

	public void makeLogForScript(McDataSelectCommonDTO mcDataSelectCommonDTO, McDataSelectDetailDTO mcDTO, String errMsg);

	/**
	 * find count
	 * @author  js.lee
	 * @param mcDataSelectCommonDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(McDataSelectCommonDTO mcDataSelectCommonDTO, User user) throws Exception;

    public McDataSelectCommonDTO findScript(McDataSelectCommonDTO mcDataSelectCommonDTO, User user) throws Exception;
    
}