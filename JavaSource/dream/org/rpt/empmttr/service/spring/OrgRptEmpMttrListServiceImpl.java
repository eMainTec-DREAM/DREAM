package dream.org.rpt.empmttr.service.spring;

import java.util.List;

import common.bean.User;
import dream.org.rpt.empmttr.dao.OrgRptEmpMttrListDAO;
import dream.org.rpt.empmttr.dto.OrgRptEmpMttrCommonDTO;
import dream.org.rpt.empmttr.service.OrgRptEmpMttrListService;

/**
 * MTTR(담당자) 목록
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="orgRptEmpMttrListServiceTarget"
 * @spring.txbn id="orgRptEmpMttrListService"
 * @spring.property name="orgRptEmpMttrListDAO" ref="orgRptEmpMttrListDAO"
 */
public class OrgRptEmpMttrListServiceImpl implements OrgRptEmpMttrListService
{
    private OrgRptEmpMttrListDAO orgRptEmpMttrListDAO = null;
    
	public OrgRptEmpMttrListDAO getOrgRptEmpMttrListDAO()
    {
        return orgRptEmpMttrListDAO;
    }
	
    public void setOrgRptEmpMttrListDAO(
            OrgRptEmpMttrListDAO orgRptEmpMttrListDAO)
    {
        this.orgRptEmpMttrListDAO = orgRptEmpMttrListDAO;
    }
    
    public List findList(OrgRptEmpMttrCommonDTO orgRptEmpMttrCommonDTO, User loginUser)
    {
        return orgRptEmpMttrListDAO.findList(orgRptEmpMttrCommonDTO, loginUser);
    }

    @Override
    public String findTotalCount(OrgRptEmpMttrCommonDTO orgRptEmpMttrCommonDTO, User user)
    {
        return orgRptEmpMttrListDAO.findTotalCount(orgRptEmpMttrCommonDTO, user);
    }
	
}

