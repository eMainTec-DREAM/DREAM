package dream.org.rpt.craft.work.time.service.spring;

import java.util.List;

import common.bean.User;
import dream.org.rpt.craft.work.time.dao.OrgRptCraftWorkTimeDailyDAO;
import dream.org.rpt.craft.work.time.dto.OrgRptCraftWorkTimeDailyDTO;
import dream.org.rpt.craft.work.time.service.OrgRptCraftWorkTimeDailyService;

/**
 * 작업자 일별 작업시간
 * @author  js.lee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="orgRptCraftWorkTimeDailyServiceTarget"
 * @spring.txbn id="orgRptCraftWorkTimeDailyService"
 * @spring.property name="orgRptCraftWorkTimeDailyDAO" ref="orgRptCraftWorkTimeDailyDAO"
 */
public class OrgRptCraftWorkTimeDailyServiceImpl implements OrgRptCraftWorkTimeDailyService
{
    private OrgRptCraftWorkTimeDailyDAO orgRptCraftWorkTimeDailyDAO = null;
    
    public OrgRptCraftWorkTimeDailyDAO getOrgRptCraftWorkTimeDailyDAO()
    {
        return orgRptCraftWorkTimeDailyDAO;
    }
    
    public void setOrgRptCraftWorkTimeDailyDAO(
            OrgRptCraftWorkTimeDailyDAO orgRptCraftWorkTimeDailyDAO)
    {
        this.orgRptCraftWorkTimeDailyDAO = orgRptCraftWorkTimeDailyDAO;
    }
    
	@Override
	public List findList(OrgRptCraftWorkTimeDailyDTO orgRptCraftWorkTimeDailyDTO, User user) {
		return orgRptCraftWorkTimeDailyDAO.findList(orgRptCraftWorkTimeDailyDTO, user);
	}

	@Override
	public String findTotalCount(OrgRptCraftWorkTimeDailyDTO orgRptCraftWorkTimeDailyDTO, User user) {
		return orgRptCraftWorkTimeDailyDAO.findTotalCount(orgRptCraftWorkTimeDailyDTO, user);
	}
}

