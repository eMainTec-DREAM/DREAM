package dream.req.rpt.woreqrate.service.spring;

import java.util.List;

import common.bean.User;
import dream.req.rpt.woreqrate.dao.ReqRptWoReqRateListDAO;
import dream.req.rpt.woreqrate.dto.ReqRptWoReqRateCommonDTO;
import dream.req.rpt.woreqrate.service.ReqRptWoReqRateListService;

/**
 * 夸没立荐啦(贸府磊) 格废
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="reqRptWoReqRateListServiceTarget"
 * @spring.txbn id="reqRptWoReqRateListService"
 * @spring.property name="reqRptWoReqRateListDAO" ref="reqRptWoReqRateListDAO"
 */
public class ReqRptWoReqRateListServiceImpl implements ReqRptWoReqRateListService
{
    private ReqRptWoReqRateListDAO reqRptWoReqRateListDAO = null;
    
	public ReqRptWoReqRateListDAO getReqRptWoReqRateListDAO()
    {
        return reqRptWoReqRateListDAO;
    }
	
    public void setReqRptWoReqRateListDAO(
            ReqRptWoReqRateListDAO reqRptWoReqRateListDAO)
    {
        this.reqRptWoReqRateListDAO = reqRptWoReqRateListDAO;
    }
    
    public List findList(ReqRptWoReqRateCommonDTO reqRptWoReqRateCommonDTO, User loginUser)
    {
        return reqRptWoReqRateListDAO.findList(reqRptWoReqRateCommonDTO, loginUser);
    }

    @Override
    public String findTotalCount(ReqRptWoReqRateCommonDTO reqRptWoReqRateCommonDTO, User user)
    {
        return reqRptWoReqRateListDAO.findTotalCount(reqRptWoReqRateCommonDTO, user);
    }
	
}

