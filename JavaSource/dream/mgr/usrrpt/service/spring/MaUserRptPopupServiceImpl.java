package dream.mgr.usrrpt.service.spring;

import java.util.List;

import common.bean.User;
import dream.mgr.usrrpt.dao.MaUserRptDetailDAO;
import dream.mgr.usrrpt.dao.MaUserRptPopupDAO;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;
import dream.mgr.usrrpt.service.MaUserRptPopupService;



/**
 * 메뉴 - 목록 serviceimpl
 * @author kim21017
 * @version $Id: MaUserRptPopupServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maUserRptPopupServiceTarget"
 * @spring.txbn id="maUserRptPopupService"
 * @spring.property name="maUserRptPopupDAO" ref="maUserRptPopupDAO"
 * @spring.property name="maUserRptDetailDAO" ref="maUserRptDetailDAO"
 */
public class MaUserRptPopupServiceImpl implements MaUserRptPopupService
{
    private MaUserRptPopupDAO maUserRptPopupDAO = null;
    
    private MaUserRptDetailDAO maUserRptDetailDAO = null;
    
    public MaUserRptDetailDAO getMaUserRptDetailDAO() {
		return maUserRptDetailDAO;
	}

	public void setMaUserRptDetailDAO(MaUserRptDetailDAO maUserRptDetailDAO) {
		this.maUserRptDetailDAO = maUserRptDetailDAO;
	}

	public MaUserRptPopupDAO getMaUserRptPopupDAO() {
		return maUserRptPopupDAO;
	}

	public void setMaUserRptPopupDAO(MaUserRptPopupDAO maUserRptPopupDAO) {
		this.maUserRptPopupDAO = maUserRptPopupDAO;
	}

	public List findList(MaUserRptCommonDTO maUserRptCommonDTO, User loginUser) throws Exception
    {      
		return maUserRptPopupDAO.findList(maUserRptCommonDTO, loginUser);
    }

	public void makeLogForScript(MaUserRptCommonDTO maUserRptCommonDTO, String errMsg, User loginUser) 
	{
		
//		MaUserRptDetailDTO mcDTO =  maUserRptDetailDAO.findDetail(maUserRptCommonDTO.getUsrrptlistId(), loginUser.getLangId());
		
//		maUserRptPopupDAO.makeLogForScript(maUserRptCommonDTO, mcDTO, errMsg==""?"success":errMsg);
	}
}

