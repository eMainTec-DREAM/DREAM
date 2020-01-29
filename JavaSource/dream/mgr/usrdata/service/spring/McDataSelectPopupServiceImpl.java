package dream.mgr.usrdata.service.spring;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.mgr.usrdata.dao.McDataSelectDetailDAO;
import dream.mgr.usrdata.dao.McDataSelectPopupDAO;
import dream.mgr.usrdata.dto.McDataSelectCommonDTO;
import dream.mgr.usrdata.dto.McDataSelectDetailDTO;
import dream.mgr.usrdata.service.McDataSelectPopupService;



/**
 * 메뉴 - 목록 serviceimpl
 * @author kim21017
 * @version $Id: McDataSelectPopupServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="mcDataSelectPopupServiceTarget"
 * @spring.txbn id="mcDataSelectPopupService"
 * @spring.property name="mcDataSelectPopupDAO" ref="mcDataSelectPopupDAO"
 * @spring.property name="mcDataSelectDetailDAO" ref="mcDataSelectDetailDAO"
 */
public class McDataSelectPopupServiceImpl implements McDataSelectPopupService
{
    private McDataSelectPopupDAO mcDataSelectPopupDAO = null;
    
    private McDataSelectDetailDAO mcDataSelectDetailDAO = null;
    
    public McDataSelectDetailDAO getMcDataSelectDetailDAO() {
		return mcDataSelectDetailDAO;
	}

	public void setMcDataSelectDetailDAO(McDataSelectDetailDAO mcDataSelectDetailDAO) {
		this.mcDataSelectDetailDAO = mcDataSelectDetailDAO;
	}

	public McDataSelectPopupDAO getMcDataSelectPopupDAO() {
		return mcDataSelectPopupDAO;
	}

	public void setMcDataSelectPopupDAO(McDataSelectPopupDAO mcDataSelectPopupDAO) {
		this.mcDataSelectPopupDAO = mcDataSelectPopupDAO;
	}

	public List findList(McDataSelectCommonDTO mcDataSelectCommonDTO, User loginUser) throws Exception
    {      
		return mcDataSelectPopupDAO.findList(mcDataSelectCommonDTO, loginUser);
    }

	public void makeLogForScript(McDataSelectCommonDTO mcDataSelectCommonDTO, String errMsg, User loginUser) 
	{
		
		McDataSelectDetailDTO mcDTO =  mcDataSelectDetailDAO.findDetail(mcDataSelectCommonDTO.getUsrrptId(), loginUser.getLangId());
		
		mcDataSelectPopupDAO.makeLogForScript(mcDataSelectCommonDTO, mcDTO, errMsg==""?"success":errMsg);
	}

	@Override
	public String findTotalCount(McDataSelectCommonDTO mcDataSelectCommonDTO, User user) throws Exception {
		return mcDataSelectPopupDAO.findTotalCount(mcDataSelectCommonDTO, user);
	}

    @Override
    public McDataSelectCommonDTO findScript(McDataSelectCommonDTO mcDataSelectCommonDTO, User user) throws Exception
    {
        return mcDataSelectPopupDAO.findScript(mcDataSelectCommonDTO, user);
    }
}

