package dream.mgr.usrrpt.dao;

import common.bean.User;
import dream.mgr.usrdata.dto.McDataSelectCommonDTO;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;


/**
 * 메뉴 - 목록 dao
 * @author  kim21017
 * @version $Id: McDataSelectListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaUserRptDesignDAO 
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
    public int updateScript(MaUserRptCommonDTO mcDataSelectCommonDTO, User loginUser);

	public MaUserRptCommonDTO findScript(MaUserRptCommonDTO mcDataSelectCommonDTO);
  
}