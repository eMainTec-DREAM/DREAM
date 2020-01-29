package dream.org.rpt.empmttr.service.spring;

import java.util.List;

import common.bean.User;
import dream.org.rpt.empmttr.dao.OrgRptEmpMttrDetailDAO;
import dream.org.rpt.empmttr.dto.OrgRptEmpMttrCommonDTO;
import dream.org.rpt.empmttr.dto.OrgRptEmpMttrDetailDTO;
import dream.org.rpt.empmttr.service.OrgRptEmpMttrDetailService;

/**
 * MTTR(담당자) 상세 목록
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="orgRptEmpMttrDetailServiceTarget"
 * @spring.txbn id="orgRptEmpMttrDetailService"
 * @spring.property name="orgRptEmpMttrDetailDAO" ref="orgRptEmpMttrDetailDAO"
 */
public class OrgRptEmpMttrDetailServiceImpl implements OrgRptEmpMttrDetailService
{
    private OrgRptEmpMttrDetailDAO orgRptEmpMttrDetailDAO = null;
    
    public OrgRptEmpMttrDetailDAO getOrgRptEmpMttrDetailDAO()
    {
        return orgRptEmpMttrDetailDAO;
    }
    
    public void setOrgRptEmpMttrDetailDAO(
            OrgRptEmpMttrDetailDAO orgRptEmpMttrDetailDAO)
    {
        this.orgRptEmpMttrDetailDAO = orgRptEmpMttrDetailDAO;
    }
    
    public List findDetail(OrgRptEmpMttrCommonDTO orgRptEmpMttrCommonDTO, OrgRptEmpMttrDetailDTO orgRptEmpMttrDetailDTO, User loginUser)
    {
        return orgRptEmpMttrDetailDAO.findDetail(orgRptEmpMttrCommonDTO, orgRptEmpMttrDetailDTO, loginUser);
        
    }

	@Override
	public String findTotalCount(OrgRptEmpMttrCommonDTO orgRptEmpMttrCommonDTO, OrgRptEmpMttrDetailDTO orgRptEmpMttrDetailDTO, User user)
	{
		 return orgRptEmpMttrDetailDAO.findTotalCount(orgRptEmpMttrCommonDTO, orgRptEmpMttrDetailDTO, user);
	}
	
}

