package dream.req.work.service.spring;

import common.bean.User;
import dream.req.work.dao.ReqWorkReswoDetailDAO;
import dream.req.work.dto.MaWoReqCommonDTO;
import dream.req.work.dto.ReqWorkReswoDetailDTO;
import dream.req.work.service.ReqWorkReswoDetailService;

/**
 * 작업요청서 - 처리사항 상세 
 * @author kim2107
 * @version $Id: ReqWorkReswoDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim2107 Exp $
 * @since 1.0
 * 
 * @spring.bean id="reqWorkReswoDetailServiceTarget"
 * @spring.txbn id="reqWorkReswoDetailService"
 * @spring.property name="reqWorkReswoDetailDAO" ref="reqWorkReswoDetailDAO"
 */
public class ReqWorkReswoDetailServiceImpl implements ReqWorkReswoDetailService
{
    private ReqWorkReswoDetailDAO reqWorkReswoDetailDAO = null;
    
    public ReqWorkReswoDetailDAO getReqWorkReswoDetailDAO() {
		return reqWorkReswoDetailDAO;
	}

	public void setReqWorkReswoDetailDAO(ReqWorkReswoDetailDAO reqWorkReswoDetailDAO) {
		this.reqWorkReswoDetailDAO = reqWorkReswoDetailDAO;
	}

	public ReqWorkReswoDetailDTO findDetail(String woDayListId, String woDayActId, User user)throws Exception
    {
        return reqWorkReswoDetailDAO.findDetail(woDayListId, woDayActId, user);
    }
   
}
