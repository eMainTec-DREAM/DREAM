package dream.mgr.usrrpt.service;

import common.bean.User;
import dream.mgr.usrdata.dto.McDataSelectCommonDTO;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;


/**
 * ��� service
 * @author  kim21017
 * @version $Id: McDataSelectListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaUserRptDesignService
{     
	public MaUserRptCommonDTO findScript(MaUserRptCommonDTO mcDataSelectCommonDTO);    
	
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: McDataSelectListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maUserRptCommonDTO
     * @since   1.0
     * 
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public int updateScript(MaUserRptCommonDTO maUserRptCommonDTO, User loginUser);    


}
