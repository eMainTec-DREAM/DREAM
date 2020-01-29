package dream.req.rpt.woplancmptrate.service.spring;

import java.util.List;

import common.bean.User;
import dream.req.rpt.woplancmptrate.dao.ReqRptWoPlanCmptDetailListDAO;
import dream.req.rpt.woplancmptrate.dto.ReqRptWoPlanCmptDetailListDTO;
import dream.req.rpt.woplancmptrate.service.ReqRptWoPlanCmptDetailListService;

/**
 * 작업의뢰 초기계획 요청 목록 - List Service implements
 * @author  nhkim8548
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="reqRptWoPlanCmptDetailListServiceTarget"
 * @spring.txbn id="reqRptWoPlanCmptDetailListService"
 * @spring.property name="reqRptWoPlanCmptDetailListDAO" ref="reqRptWoPlanCmptDetailListDAO"
 */
public class ReqRptWoPlanCmptDetailListServiceImpl implements ReqRptWoPlanCmptDetailListService
{
    private ReqRptWoPlanCmptDetailListDAO reqRptWoPlanCmptDetailListDAO = null;

    public List findList(ReqRptWoPlanCmptDetailListDTO reqRptWoPlanCmptDetailListDTO, User user) throws Exception
    {      
        return reqRptWoPlanCmptDetailListDAO.findList(reqRptWoPlanCmptDetailListDTO,user);
    }

    public ReqRptWoPlanCmptDetailListDAO getReqRptWoPlanCmptDetailListDAO() {
        return reqRptWoPlanCmptDetailListDAO;
    }

    public void setReqRptWoPlanCmptDetailListDAO(ReqRptWoPlanCmptDetailListDAO reqRptWoPlanCmptDetailListDAO) {
        this.reqRptWoPlanCmptDetailListDAO = reqRptWoPlanCmptDetailListDAO;
    }    
    
    public String findTotalCount(ReqRptWoPlanCmptDetailListDTO reqRptWoPlanCmptDetailListDTO,User user)  throws Exception
    {
        return reqRptWoPlanCmptDetailListDAO.findTotalCount(reqRptWoPlanCmptDetailListDTO, user);
    }
}
