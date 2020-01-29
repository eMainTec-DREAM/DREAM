package dream.req.rpt.prewoplanrate.service.spring;

import java.util.List;

import common.bean.User;
import dream.req.rpt.prewoplanrate.dao.ReqRptPreWoPlanDetailListDAO;
import dream.req.rpt.prewoplanrate.dto.ReqRptPreWoPlanDetailListDTO;
import dream.req.rpt.prewoplanrate.service.ReqRptPreWoPlanDetailListService;

/**
 * 작업오더 사전 계획 수립률 목록 - List Service implements
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="reqRptPreWoPlanDetailListServiceTarget"
 * @spring.txbn id="reqRptPreWoPlanDetailListService"
 * @spring.property name="reqRptPreWoPlanDetailListDAO" ref="reqRptPreWoPlanDetailListDAO"
 */
public class ReqRptPreWoPlanDetailListServiceImpl implements ReqRptPreWoPlanDetailListService
{
    private ReqRptPreWoPlanDetailListDAO reqRptPreWoPlanDetailListDAO = null;

    public List findList(ReqRptPreWoPlanDetailListDTO reqRptPreWoPlanDetailListDTO, User user) throws Exception
    {      
        return reqRptPreWoPlanDetailListDAO.findList(reqRptPreWoPlanDetailListDTO,user);
    }

    public ReqRptPreWoPlanDetailListDAO getReqRptPreWoPlanDetailListDAO() {
        return reqRptPreWoPlanDetailListDAO;
    }

    public void setReqRptPreWoPlanDetailListDAO(ReqRptPreWoPlanDetailListDAO reqRptPreWoPlanDetailListDAO) {
        this.reqRptPreWoPlanDetailListDAO = reqRptPreWoPlanDetailListDAO;
    }    
    
    public String findTotalCount(ReqRptPreWoPlanDetailListDTO reqRptPreWoPlanDetailListDTO,User user)  throws Exception
    {
        return reqRptPreWoPlanDetailListDAO.findTotalCount(reqRptPreWoPlanDetailListDTO, user);
    }
}
