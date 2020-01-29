package dream.mgr.usrdata.service.spring;

import common.bean.User;
import dream.mgr.usrdata.dao.McDataSelectScriptDAO;
import dream.mgr.usrdata.dto.McDataSelectCommonDTO;
import dream.mgr.usrdata.service.McDataSelectScriptService;



/**
 * 메뉴 - 목록 serviceimpl
 * @author kim21017
 * @version $Id: McDataSelectListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="mcDataSelectScriptServiceTarget"
 * @spring.txbn id="mcDataSelectScriptService"
 * @spring.property name="mcDataSelectScriptDAO" ref="mcDataSelectScriptDAO"
 */
public class McDataSelectScriptServiceImpl implements McDataSelectScriptService
{
    private McDataSelectScriptDAO mcDataSelectScriptDAO = null;

    public McDataSelectScriptDAO getMcDataSelectScriptDAO() {
		return mcDataSelectScriptDAO;
	}

	public void setMcDataSelectScriptDAO(McDataSelectScriptDAO mcDataSelectScriptDAO) {
		this.mcDataSelectScriptDAO = mcDataSelectScriptDAO;
	}

	public int updateScript(McDataSelectCommonDTO mcDataSelectCommonDTO, User loginUser)
    {      
        return mcDataSelectScriptDAO.updateScript(mcDataSelectCommonDTO, loginUser);
    }

	public McDataSelectCommonDTO findScript(McDataSelectCommonDTO mcDataSelectCommonDTO) {
		return mcDataSelectScriptDAO.findScript(mcDataSelectCommonDTO);
	}
}

