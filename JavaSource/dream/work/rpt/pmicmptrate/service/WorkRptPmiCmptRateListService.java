package dream.work.rpt.pmicmptrate.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.pmicmptrate.dto.WorkRptPmiCmptRateCommonDTO;

/**
 * ���������׸� ���� ���� ��� - List Service
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 */
public interface WorkRptPmiCmptRateListService
{
    /**
     * FIND LIST
     * @param workRptPmiCmptRateCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public List findList(WorkRptPmiCmptRateCommonDTO workRptPmiCmptRateCommonDTO, User user) throws Exception;
    
    /**
     * find Total Count
     * @author  js.lee
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptPmiCmptRateCommonDTO
     * @return
     */
    public String findTotalCount(WorkRptPmiCmptRateCommonDTO workRptPmiCmptRateCommonDTO, User user) throws Exception;
}
