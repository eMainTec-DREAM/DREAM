package dream.mgr.msgrec.service.spring;

import common.bean.User;
import dream.mgr.msgrec.dao.MgrMsgRecDetailDAO;
import dream.mgr.msgrec.dto.MgrMsgRecCommonDTO;
import dream.mgr.msgrec.dto.MgrMsgRecDetailDTO;
import dream.mgr.msgrec.service.MgrMsgRecDetailService;

/**
 * MgrMsgRec Page - Detail Service implements
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @spring.bean id="mgrMsgRecDetailServiceTarget"
 * @spring.txbn id="mgrMsgRecDetailService"
 * @spring.property name="mgrMsgRecDetailDAO" ref="mgrMsgRecDetailDAO"
 */
public class MgrMsgRecDetailServiceImpl implements MgrMsgRecDetailService
{
    private MgrMsgRecDetailDAO mgrMsgRecDetailDAO = null;
    
    public MgrMsgRecDetailDTO findDetail(MgrMsgRecCommonDTO mgrMsgRecCommonDTO, User user) throws Exception
    {
        return mgrMsgRecDetailDAO.findDetail(mgrMsgRecCommonDTO, user);
    }
    
    public MgrMsgRecDetailDAO getMgrMsgRecDetailDAO() {
        return mgrMsgRecDetailDAO;
    }

    public void setMgrMsgRecDetailDAO(MgrMsgRecDetailDAO mgrMsgRecDetailDAO) {
        this.mgrMsgRecDetailDAO = mgrMsgRecDetailDAO;
    }

	public int updateDetail(MgrMsgRecDetailDTO mgrMsgRecDetailDTO, User user) throws Exception 
	{
		mgrMsgRecDetailDAO.updateLang(mgrMsgRecDetailDTO, user);
		return mgrMsgRecDetailDAO.updateDetail(mgrMsgRecDetailDTO, user); 
	}

	public String validMsgObjType(MgrMsgRecDetailDTO mgrMsgRecDetailDTO, User user) throws Exception 
	{
		return mgrMsgRecDetailDAO.validMsgObjType(mgrMsgRecDetailDTO, user);
	}

	public int insertDetail(MgrMsgRecDetailDTO mgrMsgRecDetailDTO, User user) throws Exception 
	{
		mgrMsgRecDetailDAO.updateLang(mgrMsgRecDetailDTO, user);
		return mgrMsgRecDetailDAO.insertDetail(mgrMsgRecDetailDTO, user);
	}
}
