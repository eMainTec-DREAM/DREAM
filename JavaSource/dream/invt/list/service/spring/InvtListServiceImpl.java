package dream.invt.list.service.spring;

import java.io.IOException;
import java.util.List;

import common.bean.User;
import dream.invt.list.dao.InvtListDAO;
import dream.invt.list.dto.InvtCommonDTO;
import dream.invt.list.form.InvtListForm;
import dream.invt.list.service.InvtListService;

/**
 * 목록 serviceimpl
 * @author kim21017
 * @version $Id: InvtListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="invtListServiceTarget"
 * @spring.txbn id="invtListService"
 * @spring.property name="invtListDAO" ref="invtListDAO"
 */
public class InvtListServiceImpl implements InvtListService
{
    private InvtListDAO invtListDAO = null;

    public InvtListDAO getInvtListDAO() {
		return invtListDAO;
	}

	public void setInvtListDAO(InvtListDAO invtListDAO) {
		this.invtListDAO = invtListDAO;
	}

	public List findList(InvtCommonDTO invtCommonDTO, User user) throws IOException {
		// TODO Auto-generated method stub
	    
		return invtListDAO.findList(invtCommonDTO, user);
	}

	@Override
	public int deleteList(String[] deleteRows, User loginUser) {
		int result = 0;

        if(!deleteRows.equals(null))
        {
            for(String id : deleteRows)
            {
                // 첨부파일 자재별 거래처 삭제후 자재 삭제 
                result = result + invtListDAO.deletePhase(id, loginUser);
                result = result + invtListDAO.deleteInvtList(id, loginUser);
            }
        }
        return result;
	}
    public String findTotalCount(InvtCommonDTO invtCommonDTO,User user)  throws Exception
    {
        return invtListDAO.findTotalCount(invtCommonDTO, user);
    }

	public String getData(User user)
	{
		return invtListDAO.getData(user);
	}
}

