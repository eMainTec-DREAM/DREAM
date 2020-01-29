package dream.comm.revision.service.spring;

import java.io.IOException;

import common.bean.User;
import dream.comm.revision.dto.CommRevCommonDTO;
import dream.comm.revision.service.CommRevService;

public abstract class AbstractCommRevService implements CommRevService {

	protected CommRevService commRevService;
	
	public AbstractCommRevService(CommRevService commRevService)
	{
		this.commRevService = commRevService;
	}
	
	public abstract int insertRegislate(CommRevCommonDTO commRevCommonDTO, User user) throws IOException;
}
