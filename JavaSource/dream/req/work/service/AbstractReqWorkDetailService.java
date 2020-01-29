package dream.req.work.service;

import common.bean.User;
import dream.pers.appreq.dto.AppReqDetailDTO;
import dream.req.work.dto.ReqWorkCommonDTO;
import dream.req.work.dto.ReqWorkDetailDTO;

public abstract class AbstractReqWorkDetailService implements ReqWorkDetailService {

	protected ReqWorkDetailService reqWorkDetailService;
	
	public AbstractReqWorkDetailService(ReqWorkDetailService reqWorkDetailService)
	{
		this.reqWorkDetailService = reqWorkDetailService;
	}
	
	public abstract ReqWorkDetailDTO findDetail(ReqWorkCommonDTO reqWorkCommonDTO, User loginUser)throws Exception;

    public abstract int updateDetail(ReqWorkDetailDTO reqWorkDetailDTO, User user) throws Exception;

    public abstract int insertDetail(ReqWorkDetailDTO reqWorkDetailDTO, User user) throws Exception;

    public abstract int updateStatus(ReqWorkDetailDTO reqWorkDetailDTO, User user) throws Exception;

    public abstract void appProcess(AppReqDetailDTO appReqDetailDTO, User user);
}
