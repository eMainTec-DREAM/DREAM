package dream.work.rpt.pmwrate.dao;

import java.util.List;

import common.bean.User;
import dream.work.rpt.pmwrate.dto.WorkRptPmwRateCommonDTO;

/**
 * �ֱ����� ���� ���� ��� - List DAO
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 *
 */
public interface WorkRptPmwRateListDAO
{
    /**
     * FIND LIST
     * @param workRptPmwRateCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    
    public List findList(WorkRptPmwRateCommonDTO workRptPmwRateCommonDTO, User user) throws Exception;

    /**
     * FIND TOTAL LIST
     * @param workRptPmwRateCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(WorkRptPmwRateCommonDTO workRptPmwRateCommonDTO, User user) throws Exception;
    
}
