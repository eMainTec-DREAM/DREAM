package dream.invt.list.service.spring;

import common.bean.User;
import dream.invt.list.dao.InvtDetailDAO;
import dream.invt.list.dao.InvtPrcDetailDAO;
import dream.invt.list.dto.InvtCommonDTO;
import dream.invt.list.dto.InvtPrcDetailDTO;
import dream.invt.list.service.InvtPrcDetailService;
import dream.pers.appreq.dto.AppReqDetailDTO;

/**
 * 상세 serviceimpl 
 * @author  kim21017
 * @version $Id: InvtPrcDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="invtPrcDetailServiceTarget"
 * @spring.txbn id="invtPrcDetailService"
 * @spring.property name="invtPrcDetailDAO" ref="invtPrcDetailDAO"
 * @spring.property name="invtDetailDAO" ref="invtDetailDAO"
 */
public class InvtPrcDetailServiceImpl implements InvtPrcDetailService
{
    private InvtPrcDetailDAO invtPrcDetailDAO = null;
    private InvtDetailDAO invtDetailDAO = null;
    
    public InvtPrcDetailDAO getInvtPrcDetailDAO() {
		return invtPrcDetailDAO;
	}

	public void setInvtPrcDetailDAO(InvtPrcDetailDAO invtPrcDetailDAO) {
		this.invtPrcDetailDAO = invtPrcDetailDAO;
	}

	public InvtPrcDetailDTO findDetail(InvtCommonDTO invtCommonDTO, User user)throws Exception
    {
        return invtPrcDetailDAO.findDetail(invtCommonDTO, user);
    }
	
	public InvtDetailDAO getInvtDetailDAO()
    {
        return invtDetailDAO;
    }

    public void setInvtDetailDAO(InvtDetailDAO invtDetailDAO)
    {
        this.invtDetailDAO = invtDetailDAO;
    }

    public int updateDetail(InvtPrcDetailDTO invtPrcDetailDTO, User user) throws Exception
    {        
        return invtPrcDetailDAO.updateDetail(invtPrcDetailDTO, user);
    }

	public int insertDetail(InvtPrcDetailDTO invtPrcDetailDTO, User user) throws Exception
    {        
		invtPrcDetailDAO.insertPhase(invtPrcDetailDTO, user);
		//투자목록 상태 업데이트
		AppReqDetailDTO appReqDetailDTO = new AppReqDetailDTO();
        appReqDetailDTO.setObjectId(invtPrcDetailDTO.getInvtlistId());
        invtDetailDAO.updateInvtListStatus(appReqDetailDTO, user);

        return 0;
    }
	public String checkPrc(InvtCommonDTO invtCommonDTO,InvtPrcDetailDTO invtPrcDetailDTO, User user) throws Exception
    {        
		return invtPrcDetailDAO.checkPrc(invtCommonDTO, invtPrcDetailDTO, user);
    }

	public void appProcess(AppReqDetailDTO appReqDetailDTO, User user)
    {
        
	    invtPrcDetailDAO.setStatus(appReqDetailDTO, user);
        //
	    String invtListId = invtDetailDAO.getInvtListId(appReqDetailDTO.getObjectId(), user);
        //투자목록 상태 업데이트
        appReqDetailDTO.setObjectId(invtListId);
	    invtDetailDAO.updateInvtListStatus(appReqDetailDTO, user);
    }

    @Override
    public void completeDetail(InvtPrcDetailDTO invtPrcDetailDTO, User user)
    {
        AppReqDetailDTO appReqDetailDTO = new AppReqDetailDTO();
        appReqDetailDTO.setParentStatus("C");
        appReqDetailDTO.setObjectId(invtPrcDetailDTO.getInvtphaseId());
        
        this.appProcess(appReqDetailDTO, user);
    }
}
