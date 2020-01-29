package dream.work.rpt.pmwcmptrate.dao;

import java.util.List;

import common.bean.User;
import dream.work.rpt.pmwcmptrate.dto.WorkRptPmwCmptRateCommonDTO;

/**
 * �ֱ����� ��ȹ��� ���� ���� ��� - List DAO
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 *
 */
public interface WorkRptPmwCmptRateListDAO
{
    /**
     * FIND LIST
     * @param workRptPmwCmptRateCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    
    public List findList(WorkRptPmwCmptRateCommonDTO workRptPmwCmptRateCommonDTO, User user) throws Exception;

    /**
     * FIND TOTAL LIST
     * @param workRptPmwCmptRateCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(WorkRptPmwCmptRateCommonDTO workRptPmwCmptRateCommonDTO, User user) throws Exception;
    
}
