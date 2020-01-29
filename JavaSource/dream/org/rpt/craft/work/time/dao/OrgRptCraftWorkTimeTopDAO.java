package dream.org.rpt.craft.work.time.dao;

import java.util.List;

import common.bean.User;
import dream.org.rpt.craft.work.time.dto.OrgRptCraftWorkTimeTopCommonDTO;

/**
 * 작업자 작업시간 Top
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 */
public interface OrgRptCraftWorkTimeTopDAO
{
    public List findList(OrgRptCraftWorkTimeTopCommonDTO orgRptCraftWorkTimeTopCommonDTO, User user);
    public List findChartList(OrgRptCraftWorkTimeTopCommonDTO orgRptCraftWorkTimeTopCommonDTO, User user);

    public String findTotalCount(OrgRptCraftWorkTimeTopCommonDTO orgRptCraftWorkTimeTopCommonDTO, User user);
    
}