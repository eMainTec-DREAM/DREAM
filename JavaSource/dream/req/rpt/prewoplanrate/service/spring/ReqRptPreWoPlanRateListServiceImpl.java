package dream.req.rpt.prewoplanrate.service.spring;

import java.util.List;

import common.bean.User;
import dream.req.rpt.prewoplanrate.dao.ReqRptPreWoPlanRateListDAO;
import dream.req.rpt.prewoplanrate.dto.ReqRptPreWoPlanRateCommonDTO;
import dream.req.rpt.prewoplanrate.service.ReqRptPreWoPlanRateListService;

/**
 * 작업오더 사전 계획 수립률 목록 - List Service implements
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="reqRptPreWoPlanRateListServiceTarget"
 * @spring.txbn id="reqRptPreWoPlanRateListService"
 * @spring.property name="reqRptPreWoPlanRateListDAO" ref="reqRptPreWoPlanRateListDAO"
 */
public class ReqRptPreWoPlanRateListServiceImpl implements ReqRptPreWoPlanRateListService
{
    private ReqRptPreWoPlanRateListDAO reqRptPreWoPlanRateListDAO = null;

    public List findList(ReqRptPreWoPlanRateCommonDTO reqRptPreWoPlanRateCommonDTO, User user) throws Exception
    {      
        return reqRptPreWoPlanRateListDAO.findList(reqRptPreWoPlanRateCommonDTO,user);
    }

    public ReqRptPreWoPlanRateListDAO getReqRptPreWoPlanRateListDAO() {
        return reqRptPreWoPlanRateListDAO;
    }

    public void setReqRptPreWoPlanRateListDAO(ReqRptPreWoPlanRateListDAO reqRptPreWoPlanRateListDAO) {
        this.reqRptPreWoPlanRateListDAO = reqRptPreWoPlanRateListDAO;
    }    
    
    public String findTotalCount(ReqRptPreWoPlanRateCommonDTO reqRptPreWoPlanRateCommonDTO,User user)  throws Exception
    {
        return reqRptPreWoPlanRateListDAO.findTotalCount(reqRptPreWoPlanRateCommonDTO, user);
    }
}
