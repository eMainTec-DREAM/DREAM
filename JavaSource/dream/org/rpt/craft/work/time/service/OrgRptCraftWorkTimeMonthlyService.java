package dream.org.rpt.craft.work.time.service;

import java.util.List;

import common.bean.User;
import dream.org.rpt.craft.work.time.dto.OrgRptCraftWorkTimeMonthlyDTO;
import dream.org.rpt.craft.work.time.dto.OrgRptCraftWorkTimeTopCommonDTO;

/**
 * �۾��� ���� �۾��ð�
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 */
public interface OrgRptCraftWorkTimeMonthlyService
{     
	
	/**
	 * �۾��� ���� �۾��ð�
	 * @author js.lee
	 * @since   1.0
	 *
	 * @param orgRptCraftWorkTimeMonthlyDTO
	 * @param user
	 * @return
	 */
    public List findList(OrgRptCraftWorkTimeMonthlyDTO orgRptCraftWorkTimeMonthlyDTO, User user);

    public String findTotalCount(OrgRptCraftWorkTimeMonthlyDTO orgRptCraftWorkTimeMonthlyDTO, User user);
}
