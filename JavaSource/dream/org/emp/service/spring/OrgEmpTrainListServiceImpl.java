package dream.org.emp.service.spring;

import java.util.List;

import common.bean.User;
import dream.org.emp.dao.OrgEmpTrainListDAO;
import dream.org.emp.dto.MaEmpCommonDTO;
import dream.org.emp.dto.OrgEmpTrainListDTO;
import dream.org.emp.service.OrgEmpTrainListService;

/**
 * 구매신청item 목록 serviceimpl
 * @author kim21017
 * @version $Id: PartAdjStkTakeListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="orgEmpTrainListServiceTarget"
 * @spring.txbn id="orgEmpTrainListService"
 * @spring.property name="orgEmpTrainListDAO" ref="orgEmpTrainListDAO"
 */
public class OrgEmpTrainListServiceImpl implements OrgEmpTrainListService
{
    private OrgEmpTrainListDAO orgEmpTrainListDAO = null;

    public OrgEmpTrainListDAO getOrgEmpTrainListDAO() {
		return orgEmpTrainListDAO;
	}
	public void setOrgEmpTrainListDAO(OrgEmpTrainListDAO orgEmpTrainListDAO) {
		this.orgEmpTrainListDAO = orgEmpTrainListDAO;
	}
	
	public List findItemList(MaEmpCommonDTO maEmpCommonDTO, OrgEmpTrainListDTO orgEmpTrainListDTO, User user)
    {      
        return orgEmpTrainListDAO.findItemList(maEmpCommonDTO, orgEmpTrainListDTO, user);
    }
	
	public int deleteItemList(String[] deleteRows, User user) throws Exception
    {
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + orgEmpTrainListDAO.deleteItemList(id, user);
            }
        return result;
    }
	
	@Override
	public String findTotalCount(MaEmpCommonDTO maEmpCommonDTO, OrgEmpTrainListDTO orgEmpTrainListDTO, User user) {
		return orgEmpTrainListDAO.findTotalCount(maEmpCommonDTO, orgEmpTrainListDTO, user);
	}
}

