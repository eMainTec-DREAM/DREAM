package dream.org.rpt.craft.work.time.service.spring;

import java.util.List;

import common.bean.User;
import dream.org.rpt.craft.work.time.dao.OrgRptCraftWorkTimeTopDAO;
import dream.org.rpt.craft.work.time.dto.OrgRptCraftWorkTimeTopCommonDTO;
import dream.org.rpt.craft.work.time.service.OrgRptCraftWorkTimeTopService;

/**
 * 작업자 작업시간 Top
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="orgRptCraftWorkTimeTopServiceTarget"
 * @spring.txbn id="orgRptCraftWorkTimeTopService"
 * @spring.property name="orgRptCraftWorkTimeTopDAO" ref="orgRptCraftWorkTimeTopDAO"
 */
public class OrgRptCraftWorkTimeTopServiceImpl implements OrgRptCraftWorkTimeTopService
{
    private OrgRptCraftWorkTimeTopDAO orgRptCraftWorkTimeTopDAO = null;
    
	public OrgRptCraftWorkTimeTopDAO getOrgRptCraftWorkTimeTopDAO()
    {
        return orgRptCraftWorkTimeTopDAO;
    }
	
    public void setOrgRptCraftWorkTimeTopDAO(
            OrgRptCraftWorkTimeTopDAO orgRptCraftWorkTimeTopDAO)
    {
        this.orgRptCraftWorkTimeTopDAO = orgRptCraftWorkTimeTopDAO;
    }
    
    public List findList(OrgRptCraftWorkTimeTopCommonDTO orgRptCraftWorkTimeTopCommonDTO, User user)
    {
        return orgRptCraftWorkTimeTopDAO.findList(orgRptCraftWorkTimeTopCommonDTO, user);
    }

    @Override
    public String findTotalCount(OrgRptCraftWorkTimeTopCommonDTO orgRptCraftWorkTimeTopCommonDTO, User user)
    {
        return orgRptCraftWorkTimeTopDAO.findTotalCount(orgRptCraftWorkTimeTopCommonDTO, user);
    }

	@Override
	public List findChartList(OrgRptCraftWorkTimeTopCommonDTO orgRptCraftWorkTimeTopCommonDTO, User user) {
		// TODO Auto-generated method stub
		return orgRptCraftWorkTimeTopDAO.findChartList(orgRptCraftWorkTimeTopCommonDTO, user);
	}
	
}

