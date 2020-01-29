package dream.invt.list.service.spring;

import java.util.List;

import common.bean.User;
import dream.invt.list.dao.InvtDetailDAO;
import dream.invt.list.dao.InvtPrcListDAO;
import dream.invt.list.dto.InvtCommonDTO;
import dream.invt.list.service.InvtPrcListService;
import dream.pers.appreq.dto.AppReqDetailDTO;

/**
 * 목록 serviceimpl
 * @author kim21017
 * @version $Id: InvtPrcListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="invtPrcListServiceTarget"
 * @spring.txbn id="invtPrcListService"
 * @spring.property name="invtPrcListDAO" ref="invtPrcListDAO"
 * @spring.property name="invtDetailDAO" ref="invtDetailDAO"
 */
public class InvtPrcListServiceImpl implements InvtPrcListService
{
    private InvtPrcListDAO invtPrcListDAO = null;
    private InvtDetailDAO invtDetailDAO = null;

    public InvtPrcListDAO getInvtPrcListDAO() {
		return invtPrcListDAO;
	}

	public void setInvtPrcListDAO(InvtPrcListDAO invtPrcListDAO) {
		this.invtPrcListDAO = invtPrcListDAO;
	}
	
	public InvtDetailDAO getInvtDetailDAO()
    {
        return invtDetailDAO;
    }

    public void setInvtDetailDAO(InvtDetailDAO invtDetailDAO)
    {
        this.invtDetailDAO = invtDetailDAO;
    }

	public List findList(InvtCommonDTO invtCommonDTO, User user) {
		// TODO Auto-generated method stub
		return invtPrcListDAO.findList(invtCommonDTO, user);
	}
	
    @Override
	public int deleteList(String[] deleteRows, User loginUser) {
		int result = 0;

        if(!deleteRows.equals(null))
        {
            for(String id : deleteRows)
            {
                String invtListId = invtDetailDAO.getInvtListId(id, loginUser);
                //투자절차 삭제
                result = result + invtPrcListDAO.deleteInvtPhase(id, loginUser);
                //투자목록 상태 업데이트
                AppReqDetailDTO appReqDetailDTO = new AppReqDetailDTO();
                appReqDetailDTO.setObjectId(invtListId);
                invtDetailDAO.updateInvtListStatus(appReqDetailDTO, loginUser);
            }
        }
        return result;
	}
    public String findTotalCount(InvtCommonDTO invtCommonDTO,User user)  throws Exception
    {
        return invtPrcListDAO.findTotalCount(invtCommonDTO, user);
    }
}

