package dream.org.rpt.craft.work.time.dao;

import java.util.List;

import common.bean.User;
import dream.org.rpt.craft.work.time.dto.OrgRptCraftWorkTimeDailyDTO;

/**
 * �۾��� �Ϻ� �۾��ð�
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 */
public interface OrgRptCraftWorkTimeDailyDAO
{
    /**
     * �۾��� �Ϻ� �۾��ð�
     * @author  js.lee
     * @version $Id:$   
     * @since   1.0
     * 
     * @param orgRptCraftWorkTimeDailyDTO
     * @param user
     * @return List
     */
    public List findList(OrgRptCraftWorkTimeDailyDTO orgRptCraftWorkTimeDailyDTO, User user);

    public String findTotalCount(OrgRptCraftWorkTimeDailyDTO orgRptCraftWorkTimeDailyDTO, User user);
}