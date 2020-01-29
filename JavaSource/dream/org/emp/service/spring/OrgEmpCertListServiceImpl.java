package dream.org.emp.service.spring;

import java.util.List;

import common.bean.User;
import dream.org.emp.dao.OrgEmpCertListDAO;
import dream.org.emp.dto.MaEmpCommonDTO;
import dream.org.emp.dto.OrgEmpCertListDTO;
import dream.org.emp.service.OrgEmpCertListService;

/**
 * 구매신청item 목록 serviceimpl
 * @author kim21017
 * @version $Id: PartAdjStkTakeListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="orgEmpCertListServiceTarget"
 * @spring.txbn id="orgEmpCertListService"
 * @spring.property name="orgEmpCertListDAO" ref="orgEmpCertListDAO"
 */
public class OrgEmpCertListServiceImpl implements OrgEmpCertListService
{
    private OrgEmpCertListDAO orgEmpCertListDAO = null;

    public OrgEmpCertListDAO getOrgEmpCertListDAO() {
		return orgEmpCertListDAO;
	}
	public void setOrgEmpCertListDAO(OrgEmpCertListDAO orgEmpCertListDAO) {
		this.orgEmpCertListDAO = orgEmpCertListDAO;
	}
	
	public List findItemList(MaEmpCommonDTO maEmpCommonDTO, OrgEmpCertListDTO orgEmpCertListDTO, User user)
    {      
        return orgEmpCertListDAO.findItemList(maEmpCommonDTO, orgEmpCertListDTO, user);
    }
	
	public int deleteItemList(String[] deleteRows , String[] deleteRowsExt, User user) throws Exception{
        int result = 0;
        for(int i = 0; deleteRows.length > i ; i++)
        {
            result = result + orgEmpCertListDAO.deleteItemList(deleteRows[i], deleteRowsExt[i], user);
        }
        
        return result;
    }
	@Override
	public String findTotalCount(MaEmpCommonDTO maEmpCommonDTO, OrgEmpCertListDTO orgEmpCertListDTO, User user) {
		return orgEmpCertListDAO.findTotalCount(maEmpCommonDTO, orgEmpCertListDTO, user);
	}
}

