package dream.mgr.usrdata.dao;

import common.bean.User;
import dream.mgr.usrdata.dto.McDataSelectCommonDTO;


/**
 * 메뉴 - 목록 dao
 * @author  kim21017
 * @version $Id: McDataSelectListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface McDataSelectScriptDAO 
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
    public int updateScript(McDataSelectCommonDTO mcDataSelectCommonDTO, User loginUser);

	public McDataSelectCommonDTO findScript(McDataSelectCommonDTO mcDataSelectCommonDTO);
  
}