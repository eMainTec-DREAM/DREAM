package dream.req.rpt.emwogenrate.service.spring;

import java.util.List;

import common.bean.User;
import dream.req.rpt.emwogenrate.dao.ReqRptEmWoGenDetailDAO;
import dream.req.rpt.emwogenrate.dto.ReqRptEmWoGenRateCommonDTO;
import dream.req.rpt.emwogenrate.service.ReqRptEmWoGenDetailService;

/**
 * 작업의뢰 작업발행율 상세 목록
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="reqRptEmWoGenDetailServiceTarget"
 * @spring.txbn id="reqRptEmWoGenDetailService"
 * @spring.property name="reqRptEmWoGenDetailDAO" ref="reqRptEmWoGenDetailDAO"
 */
public class ReqRptEmWoGenDetailServiceImpl implements ReqRptEmWoGenDetailService
{
    private ReqRptEmWoGenDetailDAO reqRptEmWoGenDetailDAO = null;
    
    public ReqRptEmWoGenDetailDAO getReqRptEmWoGenDetailDAO()
    {
        return reqRptEmWoGenDetailDAO;
    }
    
    public void setReqRptEmWoGenDetailDAO(
            ReqRptEmWoGenDetailDAO reqRptEmWoGenDetailDAO)
    {
        this.reqRptEmWoGenDetailDAO = reqRptEmWoGenDetailDAO;
    }
    
    public List findDetail(ReqRptEmWoGenRateCommonDTO reqRptEmWoGenRateCommonDTO, User loginUser) throws Exception
    {
        return reqRptEmWoGenDetailDAO.findDetail(reqRptEmWoGenRateCommonDTO, loginUser);
        
    }

	@Override
	public String findTotalCount(ReqRptEmWoGenRateCommonDTO reqRptEmWoGenRateCommonDTO, User user)  throws Exception{
		// TODO Auto-generated method stub
		return reqRptEmWoGenDetailDAO.findTotalCount(reqRptEmWoGenRateCommonDTO, user);
	}
	
}

