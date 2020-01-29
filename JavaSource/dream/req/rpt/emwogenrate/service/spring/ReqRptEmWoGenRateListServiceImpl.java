package dream.req.rpt.emwogenrate.service.spring;

import java.util.List;

import common.bean.User;
import dream.req.rpt.emwogenrate.dao.ReqRptEmWoGenRateListDAO;
import dream.req.rpt.emwogenrate.dto.ReqRptEmWoGenRateCommonDTO;
import dream.req.rpt.emwogenrate.service.ReqRptEmWoGenRateListService;

/**
 * 사후 작업오더 발생률 목록 - List Service implements
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="reqRptEmWoGenRateListServiceTarget"
 * @spring.txbn id="reqRptEmWoGenRateListService"
 * @spring.property name="reqRptEmWoGenRateListDAO" ref="reqRptEmWoGenRateListDAO"
 */
public class ReqRptEmWoGenRateListServiceImpl implements ReqRptEmWoGenRateListService
{
    private ReqRptEmWoGenRateListDAO reqRptEmWoGenRateListDAO = null;

    public List findList(ReqRptEmWoGenRateCommonDTO reqRptEmWoGenRateCommonDTO, User user) throws Exception
    {      
        return reqRptEmWoGenRateListDAO.findList(reqRptEmWoGenRateCommonDTO,user);
    }

    public ReqRptEmWoGenRateListDAO getReqRptEmWoGenRateListDAO() {
        return reqRptEmWoGenRateListDAO;
    }

    public void setReqRptEmWoGenRateListDAO(ReqRptEmWoGenRateListDAO reqRptEmWoGenRateListDAO) {
        this.reqRptEmWoGenRateListDAO = reqRptEmWoGenRateListDAO;
    }    
    
    public String findTotalCount(ReqRptEmWoGenRateCommonDTO reqRptEmWoGenRateCommonDTO,User user)  throws Exception
    {
        return reqRptEmWoGenRateListDAO.findTotalCount(reqRptEmWoGenRateCommonDTO, user);
    }
}
