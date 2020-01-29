package dream.req.work.service.spring;

import common.bean.User;
import dream.req.work.dao.ReqWorkResDetailDAO;
import dream.req.work.dto.ReqWorkCommonDTO;
import dream.req.work.dto.ReqWorkResDetailDTO;
import dream.req.work.service.ReqWorkResDetailService;

/**
 * 작업요청서 - 처리사항 상세
 * @author kim2107
 * @version $Id: ReqWorkResDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim2107 Exp $
 * @since 1.0
 *
 * @spring.bean id="reqWorkResDetailServiceTarget"
 * @spring.txbn id="reqWorkResDetailService"
 * @spring.property name="reqWorkResDetailDAO" ref="reqWorkResDetailDAO"
 */
public class ReqWorkResDetailServiceImpl implements ReqWorkResDetailService
{
    private ReqWorkResDetailDAO reqWorkResDetailDAO = null;

    public ReqWorkResDetailDAO getReqWorkResDetailDAO() {
		return reqWorkResDetailDAO;
	}

	public void setReqWorkResDetailDAO(ReqWorkResDetailDAO reqWorkResDetailDAO) {
		this.reqWorkResDetailDAO = reqWorkResDetailDAO;
	}

	public ReqWorkResDetailDTO findDetail(String woDayListId, String woDayActId, User user)throws Exception
    {
        return reqWorkResDetailDAO.findDetail(woDayListId, woDayActId, user);
    }

	public int updateDetail(ReqWorkResDetailDTO reqWorkResDetailDTO, ReqWorkCommonDTO reqWorkCommonDTO) throws Exception
    {        reqWorkResDetailDAO.updateDetail(reqWorkResDetailDTO, reqWorkCommonDTO);
        return reqWorkResDetailDAO.changeHdrStatus(reqWorkResDetailDTO, reqWorkCommonDTO);
    }
	public int insertDetail(ReqWorkResDetailDTO reqWorkResDetailDTO, ReqWorkCommonDTO reqWorkCommonDTO) throws Exception
    {        reqWorkResDetailDAO.insertDetail( reqWorkResDetailDTO, reqWorkCommonDTO);
        return reqWorkResDetailDAO.changeHdrStatus( reqWorkResDetailDTO, reqWorkCommonDTO);
    }
}
