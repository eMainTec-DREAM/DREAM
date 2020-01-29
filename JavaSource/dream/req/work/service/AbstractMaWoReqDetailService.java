package dream.req.work.service;

import common.bean.User;
import dream.req.work.dto.MaWoReqCommonDTO;
import dream.req.work.dto.MaWoReqDetailDTO;

public abstract class AbstractMaWoReqDetailService implements MaWoReqDetailService {

	protected MaWoReqDetailService maWoReqDetailService;
	
	public AbstractMaWoReqDetailService(MaWoReqDetailService maWoReqDetailService)
	{
		this.maWoReqDetailService = maWoReqDetailService;
	}

	public abstract MaWoReqDetailDTO findDetail(MaWoReqCommonDTO maWoReqCommonDTO, User loginUser)throws Exception;
   
    public abstract int updateDetail(MaWoReqDetailDTO maWoReqDetailDTO, User user) throws Exception;
   
    public abstract int insertDetail(MaWoReqDetailDTO maWoReqDetailDTO, User user) throws Exception;

    public abstract int updateStatus(MaWoReqDetailDTO maWoReqDetailDTO, User user) throws Exception;
   
    public abstract int updateIncStatus(MaWoReqDetailDTO maWoReqDetailDTO, User user) throws Exception;
    
    public abstract String checkValidRecDept(MaWoReqDetailDTO maWoReqDetailDTO, User user) throws Exception;
}
