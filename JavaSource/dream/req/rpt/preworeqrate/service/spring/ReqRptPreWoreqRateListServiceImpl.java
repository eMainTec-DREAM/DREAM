package dream.req.rpt.preworeqrate.service.spring;

import java.util.List;

import common.bean.User;
import dream.req.rpt.preworeqrate.dao.ReqRptPreWoreqRateListDAO;
import dream.req.rpt.preworeqrate.dto.ReqRptPreWoreqRateCommonDTO;
import dream.req.rpt.preworeqrate.service.ReqRptPreWoreqRateListService;

/**
 * 작업의뢰 사전 시스템 요청율 목록 - List Service implements
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="reqRptPreWoreqRateListServiceTarget"
 * @spring.txbn id="reqRptPreWoreqRateListService"
 * @spring.property name="reqRptPreWoreqRateListDAO" ref="reqRptPreWoreqRateListDAO"
 */
public class ReqRptPreWoreqRateListServiceImpl implements ReqRptPreWoreqRateListService
{
    private ReqRptPreWoreqRateListDAO reqRptPreWoreqRateListDAO = null;

    public List findList(ReqRptPreWoreqRateCommonDTO reqRptPreWoreqRateCommonDTO, User user) throws Exception
    {      
        return reqRptPreWoreqRateListDAO.findList(reqRptPreWoreqRateCommonDTO,user);
    }

    public ReqRptPreWoreqRateListDAO getReqRptPreWoreqRateListDAO() {
        return reqRptPreWoreqRateListDAO;
    }

    public void setReqRptPreWoreqRateListDAO(ReqRptPreWoreqRateListDAO reqRptPreWoreqRateListDAO) {
        this.reqRptPreWoreqRateListDAO = reqRptPreWoreqRateListDAO;
    }    
    
    public String findTotalCount(ReqRptPreWoreqRateCommonDTO reqRptPreWoreqRateCommonDTO,User user)  throws Exception
    {
        return reqRptPreWoreqRateListDAO.findTotalCount(reqRptPreWoreqRateCommonDTO, user);
    }
}
