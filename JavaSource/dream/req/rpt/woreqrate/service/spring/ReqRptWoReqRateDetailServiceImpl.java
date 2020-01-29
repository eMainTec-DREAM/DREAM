package dream.req.rpt.woreqrate.service.spring;

import java.util.List;

import common.bean.User;
import dream.req.rpt.woreqrate.dao.ReqRptWoReqRateDetailDAO;
import dream.req.rpt.woreqrate.dto.ReqRptWoReqRateDetailDTO;
import dream.req.rpt.woreqrate.service.ReqRptWoReqRateDetailService;

/**
 * 夸没立荐啦(贸府磊) 惑技 格废
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="reqRptWoReqRateDetailServiceTarget"
 * @spring.txbn id="reqRptWoReqRateDetailService"
 * @spring.property name="reqRptWoReqRateDetailDAO" ref="reqRptWoReqRateDetailDAO"
 */
public class ReqRptWoReqRateDetailServiceImpl implements ReqRptWoReqRateDetailService
{
    private ReqRptWoReqRateDetailDAO reqRptWoReqRateDetailDAO = null;
    
    public ReqRptWoReqRateDetailDAO getReqRptWoReqRateDetailDAO()
    {
        return reqRptWoReqRateDetailDAO;
    }
    
    public void setReqRptWoReqRateDetailDAO(
            ReqRptWoReqRateDetailDAO reqRptWoReqRateDetailDAO)
    {
        this.reqRptWoReqRateDetailDAO = reqRptWoReqRateDetailDAO;
    }
    
    public List findDetail(ReqRptWoReqRateDetailDTO reqRptWoReqRateDetailDTO, User loginUser)
    {
        return reqRptWoReqRateDetailDAO.findDetail(reqRptWoReqRateDetailDTO, loginUser);
    }
}

