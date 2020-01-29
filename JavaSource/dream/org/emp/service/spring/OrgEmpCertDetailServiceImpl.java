package dream.org.emp.service.spring;

import common.bean.User;
import dream.org.emp.dao.OrgEmpCertDetailDAO;
import dream.org.emp.dto.OrgEmpCertDetailDTO;
import dream.org.emp.dto.OrgEmpCertDetailDTO;
import dream.org.emp.dto.MaEmpCommonDTO;
import dream.org.emp.dto.OrgEmpCertListDTO;
import dream.org.emp.service.OrgEmpCertDetailService;

/**
 * 구매신청item - 상세
 * @author kim2107
 * @version $Id: PartAdjStkTakeDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim2107 Exp $
 * @since 1.0
 * 
 * @spring.bean id="orgEmpCertDetailServiceTarget"
 * @spring.txbn id="orgEmpCertDetailService"
 * @spring.property name="orgEmpCertDetailDAO" ref="orgEmpCertDetailDAO"
 */
public class OrgEmpCertDetailServiceImpl implements OrgEmpCertDetailService
{
    private OrgEmpCertDetailDAO orgEmpCertDetailDAO = null;
    
    public OrgEmpCertDetailDAO getOrgEmpCertDetailDAO() {
		return orgEmpCertDetailDAO;
	}

	public void setOrgEmpCertDetailDAO(OrgEmpCertDetailDAO orgEmpCertDetailDAO) {
		this.orgEmpCertDetailDAO = orgEmpCertDetailDAO;
	}

	public OrgEmpCertDetailDTO findDetail(OrgEmpCertListDTO orgEmpCertListDTO, MaEmpCommonDTO maEmpCommonDTO, User user)throws Exception
    {
        return orgEmpCertDetailDAO.findDetail(orgEmpCertListDTO, maEmpCommonDTO, user);
    }
    
	public int updateDetail(OrgEmpCertDetailDTO orgEmpCertDetailDTO, MaEmpCommonDTO maEmpCommonDTO) throws Exception
    {        
		
        return orgEmpCertDetailDAO.updateDetail(orgEmpCertDetailDTO, maEmpCommonDTO);
    }
	public int insertDetail(OrgEmpCertDetailDTO orgEmpCertDetailDTO, MaEmpCommonDTO maEmpCommonDTO) throws Exception
    {        
        return orgEmpCertDetailDAO.insertDetail( orgEmpCertDetailDTO, maEmpCommonDTO);
    }
}
