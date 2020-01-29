package dream.work.rpt.woendrate.dao;

import java.util.List;

import common.bean.User;
import dream.work.rpt.woendrate.dto.WorkRptWoEndRateCommonDTO;

/**
 * �۾����� �ϸ��� ó���� ��� - List DAO
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 *
 */
public interface WorkRptWoEndRateListDAO
{
    /**
     * FIND LIST
     * @param workRptWoEndRateCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    
    public List findList(WorkRptWoEndRateCommonDTO workRptWoEndRateCommonDTO, User user) throws Exception;

    /**
     * FIND TOTAL LIST
     * @param workRptWoEndRateCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(WorkRptWoEndRateCommonDTO workRptWoEndRateCommonDTO, User user) throws Exception;
    
}
