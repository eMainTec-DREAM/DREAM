package dream.work.rpt.wopmwcmptrate.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.wopmwcmptrate.dto.WorkRptWoPmwCmptRateCommonDTO;

/**
 * �����۾� ��ȹ��� ���� ���� ��� - List Service
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 */
public interface WorkRptWoPmwCmptRateListService
{
    /**
     * FIND LIST
     * @param workRptWoPmwCmptRateCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public List findList(WorkRptWoPmwCmptRateCommonDTO workRptWoPmwCmptRateCommonDTO, User user) throws Exception;
    
    /**
     * find Total Count
     * @author  js.lee
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptWoPmwCmptRateCommonDTO
     * @return
     */
    public String findTotalCount(WorkRptWoPmwCmptRateCommonDTO workRptWoPmwCmptRateCommonDTO, User user) throws Exception;
}
