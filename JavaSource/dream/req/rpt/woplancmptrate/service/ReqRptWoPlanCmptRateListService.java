package dream.req.rpt.woplancmptrate.service;

import java.util.List;

import common.bean.User;
import dream.req.rpt.woplancmptrate.dto.ReqRptWoPlanCmptRateCommonDTO;

/**
 * �۾��Ƿ� ��ȹ��� ���� ���� ��� - List Service
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 */
public interface ReqRptWoPlanCmptRateListService
{
    /**
     * FIND LIST
     * @param reqRptWoPlanCmptRateCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public List findList(ReqRptWoPlanCmptRateCommonDTO reqRptWoPlanCmptRateCommonDTO, User user) throws Exception;
    
    /**
     * find Total Count
     * @author  js.lee
     * @version $Id:$
     * @since   1.0
     * 
     * @param reqRptWoPlanCmptRateCommonDTO
     * @return
     */
    public String findTotalCount(ReqRptWoPlanCmptRateCommonDTO reqRptWoPlanCmptRateCommonDTO, User user) throws Exception;
}
