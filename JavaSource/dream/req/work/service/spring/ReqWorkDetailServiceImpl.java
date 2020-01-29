package dream.req.work.service.spring;

import common.bean.User;
import common.util.MailUtil;
import common.util.MessageSendUtil;
import dream.mgr.message.service.MgrMessageTransDetailService;
import dream.pers.appreq.dto.AppReqDetailDTO;
import dream.req.work.dao.ReqWorkDetailDAO;
import dream.req.work.dto.ReqWorkCommonDTO;
import dream.req.work.dto.ReqWorkDetailDTO;
import dream.req.work.service.ReqWorkDetailService;

/**
 * 작업요청서 - 상세 serviceimpl
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="reqWorkDetailServiceTarget"
 * @spring.txbn id="reqWorkDetailService"
 * @spring.property name="reqWorkDetailDAO" ref="reqWorkDetailDAO"
 * @spring.property name="mgrMessageTransDetailService" ref="mgrMessageTransDetailService"
 */
public class ReqWorkDetailServiceImpl implements ReqWorkDetailService
{
    private ReqWorkDetailDAO reqWorkDetailDAO = null;
    private MgrMessageTransDetailService mgrMessageTransDetailService = null;

    public MgrMessageTransDetailService getMgrMessageTransDetailService()
    {
        return mgrMessageTransDetailService;
    }

    public void setMgrMessageTransDetailService(
            MgrMessageTransDetailService mgrMessageTransDetailService)
    {
        this.mgrMessageTransDetailService = mgrMessageTransDetailService;
    }

    public ReqWorkDetailDAO getReqWorkDetailDAO()
    {
		return reqWorkDetailDAO;
	}

	public void setReqWorkDetailDAO(ReqWorkDetailDAO reqWorkDetailDAO)
	{
		this.reqWorkDetailDAO = reqWorkDetailDAO;
	}

	public ReqWorkDetailDTO findDetail(ReqWorkCommonDTO reqWorkCommonDTO, User loginUser)throws Exception
    {

        return reqWorkDetailDAO.findDetail(reqWorkCommonDTO, loginUser);
    }

	public int updateDetail(ReqWorkDetailDTO reqWorkDetailDTO, User user) throws Exception
    {
	    int resultCnt = 0;
	    resultCnt = reqWorkDetailDAO.updateDetail(reqWorkDetailDTO,user);
        return resultCnt;
    }
	public int insertDetail(ReqWorkDetailDTO reqWorkDetailDTO, User user) throws Exception
    {
	    int resultCnt = 0;
	    resultCnt = reqWorkDetailDAO.insertDetail(reqWorkDetailDTO,user);
        return resultCnt;
    }

    @Override
    public int updateStatus(final ReqWorkDetailDTO reqWorkDetailDTO, final User user) throws Exception
    {
        int resultCnt = 0;
        resultCnt = reqWorkDetailDAO.updateStatus(reqWorkDetailDTO, user);
        
        MailUtil.sendMail("REQ10", reqWorkDetailDTO.getWoReqId(), user);
        MessageSendUtil.sendKakaoAlarm("REQ10", reqWorkDetailDTO.getWoReqId(), user);
        
        return resultCnt;
    }

    @Override
    public void appProcess(AppReqDetailDTO appReqDetailDTO, User user)
    {
        if("REQ".equals(appReqDetailDTO.getParentStatus())) {
            try
            {
                ReqWorkCommonDTO reqWorkCommonDTO = new ReqWorkCommonDTO();
                reqWorkCommonDTO.setWoReqId(appReqDetailDTO.getObjectId());
                ReqWorkDetailDTO reqWorkDetailDTO = this.findDetail(reqWorkCommonDTO, user);
                this.updateStatus(reqWorkDetailDTO, user);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else {
            reqWorkDetailDAO.setStatus(appReqDetailDTO, user);
        }
    }
}
