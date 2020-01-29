package dream.req.work.service.spring;

import common.bean.User;
import dream.req.work.dao.ReqInvWorkResDetailDAO;
import dream.req.work.dto.ReqInvWorkResDetailDTO;
import dream.req.work.dto.ReqWorkResListDTO;
import dream.req.work.service.ReqInvWorkResDetailService;

/**
 * 작업요청서(투자) - 처리사항 상세
 * @author js.lee
 * @version $Id: $
 * @since 1.0
 *
 * @spring.bean id="reqInvWorkResDetailServiceTarget"
 * @spring.txbn id="reqInvWorkResDetailService"
 * @spring.property name="reqInvWorkResDetailDAO" ref="reqInvWorkResDetailDAO"
 */
public class ReqInvWorkResDetailServiceImpl implements ReqInvWorkResDetailService
{
    private ReqInvWorkResDetailDAO reqInvWorkResDetailDAO = null;

    public ReqInvWorkResDetailDAO getInvReqWorkResDetailDAO() {
		return reqInvWorkResDetailDAO;
	}

	public void setReqInvWorkResDetailDAO(ReqInvWorkResDetailDAO reqInvWorkResDetailDAO) {
		this.reqInvWorkResDetailDAO = reqInvWorkResDetailDAO;
	}

	@Override
	public ReqInvWorkResDetailDTO findDetail(ReqWorkResListDTO reqWorkResListDTO, User user) throws Exception {
		return reqInvWorkResDetailDAO.findDetail(reqWorkResListDTO, user);
	}
}
