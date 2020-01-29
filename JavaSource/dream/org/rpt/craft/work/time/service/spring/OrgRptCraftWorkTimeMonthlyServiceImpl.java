package dream.org.rpt.craft.work.time.service.spring;

import java.util.List;

import common.bean.User;
import dream.org.rpt.craft.work.time.dao.OrgRptCraftWorkTimeMonthlyDAO;
import dream.org.rpt.craft.work.time.dto.OrgRptCraftWorkTimeMonthlyDTO;
import dream.org.rpt.craft.work.time.service.OrgRptCraftWorkTimeMonthlyService;

/**
 * 작업자 월별 작업시간
 * @author  js.lee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="orgRptCraftWorkTimeMonthlyServiceTarget"
 * @spring.txbn id="orgRptCraftWorkTimeMonthlyService"
 * @spring.property name="orgRptCraftWorkTimeMonthlyDAO" ref="orgRptCraftWorkTimeMonthlyDAO"
 */
public class OrgRptCraftWorkTimeMonthlyServiceImpl implements OrgRptCraftWorkTimeMonthlyService
{
    private OrgRptCraftWorkTimeMonthlyDAO orgRptCraftWorkTimeMonthlyDAO = null;
    
    public OrgRptCraftWorkTimeMonthlyDAO getOrgRptCraftWorkTimeMonthlyDAO()
    {
        return orgRptCraftWorkTimeMonthlyDAO;
    }
    
    public void setOrgRptCraftWorkTimeMonthlyDAO(
            OrgRptCraftWorkTimeMonthlyDAO orgRptCraftWorkTimeMonthlyDAO)
    {
        this.orgRptCraftWorkTimeMonthlyDAO = orgRptCraftWorkTimeMonthlyDAO;
    }
    
	@Override
	public List findList(OrgRptCraftWorkTimeMonthlyDTO orgRptCraftWorkTimeMonthlyDTO, User user) {
		return orgRptCraftWorkTimeMonthlyDAO.findList(orgRptCraftWorkTimeMonthlyDTO, user);
	}

	@Override
	public String findTotalCount(OrgRptCraftWorkTimeMonthlyDTO orgRptCraftWorkTimeMonthlyDTO, User user) {
		return orgRptCraftWorkTimeMonthlyDAO.findTotalCount(orgRptCraftWorkTimeMonthlyDTO, user);
	}
}

