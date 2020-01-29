package dream.org.rpt.craft.work.time.service;

import java.util.List;

import common.bean.User;
import dream.org.rpt.craft.work.time.dto.OrgRptCraftWorkTimeDailyDTO;
import dream.org.rpt.craft.work.time.dto.OrgRptCraftWorkTimeTopCommonDTO;

/**
 * 작업자 일별 작업시간
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 */
public interface OrgRptCraftWorkTimeDailyService
{     
	
	/**
	 * 작업자 일별 작업시간
	 * @author js.lee
	 * @since   1.0
	 *
	 * @param orgRptCraftWorkTimeDailyDTO
	 * @param user
	 * @return
	 */
    public List findList(OrgRptCraftWorkTimeDailyDTO orgRptCraftWorkTimeDailyDTO, User user);

    public String findTotalCount(OrgRptCraftWorkTimeDailyDTO orgRptCraftWorkTimeDailyDTO, User user);
}
