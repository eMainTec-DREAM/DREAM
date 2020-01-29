package dream.req.rpt.woplancmptrate.service.spring;

import java.util.List;

import common.bean.User;
import dream.req.rpt.woplancmptrate.dao.ReqRptWoPlanCmptRateListDAO;
import dream.req.rpt.woplancmptrate.dto.ReqRptWoPlanCmptRateCommonDTO;
import dream.req.rpt.woplancmptrate.service.ReqRptWoPlanCmptRateListService;

/**
 * 작업의뢰 계획대비 실행 비율 목록 - List Service implements
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="reqRptWoPlanCmptRateListServiceTarget"
 * @spring.txbn id="reqRptWoPlanCmptRateListService"
 * @spring.property name="reqRptWoPlanCmptRateListDAO" ref="reqRptWoPlanCmptRateListDAO"
 */
public class ReqRptWoPlanCmptRateListServiceImpl implements ReqRptWoPlanCmptRateListService
{
    private ReqRptWoPlanCmptRateListDAO reqRptWoPlanCmptRateListDAO = null;

    public List findList(ReqRptWoPlanCmptRateCommonDTO reqRptWoPlanCmptRateCommonDTO, User user) throws Exception
    {      
        return reqRptWoPlanCmptRateListDAO.findList(reqRptWoPlanCmptRateCommonDTO,user);
    }

    public ReqRptWoPlanCmptRateListDAO getReqRptWoPlanCmptRateListDAO() {
        return reqRptWoPlanCmptRateListDAO;
    }

    public void setReqRptWoPlanCmptRateListDAO(ReqRptWoPlanCmptRateListDAO reqRptWoPlanCmptRateListDAO) {
        this.reqRptWoPlanCmptRateListDAO = reqRptWoPlanCmptRateListDAO;
    }    
    
    public String findTotalCount(ReqRptWoPlanCmptRateCommonDTO reqRptWoPlanCmptRateCommonDTO,User user)  throws Exception
    {
        return reqRptWoPlanCmptRateListDAO.findTotalCount(reqRptWoPlanCmptRateCommonDTO, user);
    }
}
