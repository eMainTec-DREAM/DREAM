package dream.req.rpt.woplancmptrate.dao;

import java.util.List;

import common.bean.User;
import dream.req.rpt.woplancmptrate.dto.ReqRptWoPlanCmptRateCommonDTO;

/**
 * �۾��Ƿ� ��ȹ��� ���� ���� ��� - List DAO
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 *
 */
public interface ReqRptWoPlanCmptRateListDAO
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
     * FIND TOTAL LIST
     * @param reqRptWoPlanCmptRateCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(ReqRptWoPlanCmptRateCommonDTO reqRptWoPlanCmptRateCommonDTO, User user) throws Exception;
    
}
