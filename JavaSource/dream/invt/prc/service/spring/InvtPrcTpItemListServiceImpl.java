package dream.invt.prc.service.spring;

import java.util.List;

import common.bean.User;
import dream.invt.prc.dao.InvtPrcTpItemListDAO;
import dream.invt.prc.dto.InvtPrcTpItemListDTO;
import dream.invt.prc.dto.InvtPrcTpCommonDTO;
import dream.invt.prc.service.InvtPrcTpItemListService;

/**
 * 구매절차 Item serviceimpl
 * @author hyosung
 * @version $Id: InvtPrcTpItemListServiceImpl.java,v 1.0 2015/12/02 09:12:51 hyosung Exp $
 * @since 1.0
 * 
 * @spring.bean id="invtPrcTpItemListServiceTarget"
 * @spring.txbn id="invtPrcTpItemListService"
 * @spring.property name="invtPrcTpItemListDAO" ref="invtPrcTpItemListDAO"
 */
public class InvtPrcTpItemListServiceImpl implements InvtPrcTpItemListService
{
    private InvtPrcTpItemListDAO invtPrcTpItemListDAO = null;

    public InvtPrcTpItemListDAO getInvtPrcTpItemListDAO() {
		return invtPrcTpItemListDAO;
	}
	public void setInvtPrcTpItemListDAO(InvtPrcTpItemListDAO invtPrcTpItemListDAO) {
		this.invtPrcTpItemListDAO = invtPrcTpItemListDAO;
	}
	
	public List findItemList(InvtPrcTpCommonDTO invtPrcTpCommonDTO, InvtPrcTpItemListDTO invtPrcTpItemListDTO,User user)
    {      
        return invtPrcTpItemListDAO.findItemList(invtPrcTpCommonDTO, invtPrcTpItemListDTO,user);
    }
	
	public int deleteItemList(String[] deleteRows ,User user) throws Exception{
        int result = 0;
        for(int i = 0; deleteRows.length > i ; i++)
        {
            result = result + invtPrcTpItemListDAO.deleteItemList(deleteRows[i],user);
        }
        
        return result;
    }
	
	@Override
	public String findTotalCount(InvtPrcTpCommonDTO invtPrcTpCommonDTO, InvtPrcTpItemListDTO invtPrcTpItemListDTO, User user)
	{
		return invtPrcTpItemListDAO.findTotalCount(invtPrcTpCommonDTO, invtPrcTpItemListDTO, user);
	}
}

