package dream.invt.list.service.spring;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.util.CommonUtil;
import dream.invt.list.dao.InvtDetailDAO;
import dream.invt.list.dao.InvtPhaseListDAO;
import dream.invt.list.dto.InvtCommonDTO;
import dream.invt.list.dto.InvtPhaseDetailDTO;
import dream.invt.list.service.InvtPhaseDetailService;
import dream.invt.list.service.InvtPhaseListService;
import dream.pers.appreq.dto.AppReqDetailDTO;
import dream.req.work.dto.MaWoReqCommonDTO;
import dream.req.work.service.MaWoReqDetailService;




/**
 * 목록 serviceimpl
 * @author kim21017
 * @version $Id: InvtPhaseListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="invtPhaseListServiceTarget"
 * @spring.txbn id="invtPhaseListService"
 * @spring.property name="invtPhaseListDAO" ref="invtPhaseListDAO"
 * @spring.property name="invtDetailDAO" ref="invtDetailDAO"
 * @spring.property name="invtPhaseDetailService" ref="invtPhaseDetailService"
 * 
 */
public class InvtPhaseListServiceImpl implements InvtPhaseListService
{
    private InvtPhaseListDAO invtPhaseListDAO = null;
    private InvtDetailDAO invtDetailDAO = null;
    private InvtPhaseDetailService invtPhaseDetailService = null;

    public InvtPhaseListDAO getInvtPhaseListDAO() {
		return invtPhaseListDAO;
	}
	public void setInvtPhaseListDAO(InvtPhaseListDAO invtPhaseListDAO) {
		this.invtPhaseListDAO = invtPhaseListDAO;
	}
	public InvtDetailDAO getInvtDetailDAO()
    {
        return invtDetailDAO;
    }
    public void setInvtDetailDAO(InvtDetailDAO invtDetailDAO)
    {
        this.invtDetailDAO = invtDetailDAO;
    }
    public InvtPhaseDetailService getInvtPhaseDetailService() {
		return invtPhaseDetailService;
	}
	public void setInvtPhaseDetailService(InvtPhaseDetailService invtPhaseDetailService) {
		this.invtPhaseDetailService = invtPhaseDetailService;
	}
	
	
	public List findList(InvtCommonDTO invtCommonDTO, User user)
    {      
        return invtPhaseListDAO.findList(invtCommonDTO, user);
    }
	
	public int deleteList(String[] deleteRows, User user) throws Exception{
        int result = 0;
        for(int i = 0; deleteRows.length > i ; i++)
        {
            String invtListId = invtDetailDAO.getInvtListId(deleteRows[i], user);
            //투자절차 삭제
            result = result + invtPhaseListDAO.deleteList(deleteRows[i], user);
        }
        
        return result;
    }
	public String findTotalCount(InvtCommonDTO invtCommonDTO,User user)  throws Exception
    {
        return invtPhaseListDAO.findTotalCount(invtCommonDTO, user);
    }
	@Override
	public void savePointList(List<Map> gridList, User user) throws Exception
	{
		InvtPhaseDetailDTO invtPhaseDetailDTO = null;
		InvtCommonDTO invtCommonDTO = null;
		
		for (Map map : gridList) {
			invtPhaseDetailDTO = (InvtPhaseDetailDTO)CommonUtil.makeDTO(map, InvtPhaseDetailDTO.class);
			invtCommonDTO = (InvtCommonDTO)CommonUtil.makeDTO(map, InvtCommonDTO.class);
			
			invtPhaseDetailDTO.setStartDate(invtPhaseDetailDTO.getStartDate());
			invtPhaseDetailDTO.setEndDate(invtPhaseDetailDTO.getEndDate());
			invtCommonDTO.setCompNo(user.getCompNo());
			
			switch (invtPhaseDetailDTO.getNativeeditor())
			{
				case "inserted":
					break;
				case "updated" : invtPhaseDetailService.updateDetail(invtPhaseDetailDTO, invtCommonDTO, user);
					break;
				case "deleted": invtPhaseListDAO.deleteList(invtPhaseDetailDTO.getInvtphaseId(), user); 
					break;
			}
		}
	}

	public int insertPhase(InvtCommonDTO invtCommonDTO, User user) throws Exception 
	{
		int result = 0;
        
		String[] multiKey = invtCommonDTO.getInvtprcphId().split("\\+");
		
        for(int i=0; i < multiKey.length; i++)
        {
            invtCommonDTO.setInvtprcphId(multiKey[i]);

            String cnt = this.validPhase(invtCommonDTO, user);
            
            if("0".equals(cnt))
            {
            	invtCommonDTO.setInvtphaseId(invtPhaseListDAO.getNextSequence("SQAINVTPHASE_ID"));
            	invtPhaseListDAO.insertPhase(invtCommonDTO, user);
            	invtCommonDTO.setInvtphaseId("");
            } 
        }
        
		return result;
	}
	
	public String validPhase(InvtCommonDTO invtCommonDTO, User user) throws Exception 
	{
		return invtPhaseListDAO.validPhase(invtCommonDTO, user);
	}
}

