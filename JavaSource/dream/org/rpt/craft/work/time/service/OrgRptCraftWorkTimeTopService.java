package dream.org.rpt.craft.work.time.service;

import java.util.List;

import common.bean.User;
import dream.org.rpt.craft.work.time.dto.OrgRptCraftWorkTimeTopCommonDTO;

/**
 * 작업자 작업시간 Top
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 */
public interface OrgRptCraftWorkTimeTopService
{     
	
    public List findList(OrgRptCraftWorkTimeTopCommonDTO orgRptCraftWorkTimeTopCommonDTO, User user);
    public List findChartList(OrgRptCraftWorkTimeTopCommonDTO orgRptCraftWorkTimeTopCommonDTO, User user);

    public String findTotalCount(OrgRptCraftWorkTimeTopCommonDTO orgRptCraftWorkTimeTopCommonDTO, User user);
    
}
