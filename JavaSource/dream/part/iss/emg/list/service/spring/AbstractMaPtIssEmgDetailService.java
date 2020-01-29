package dream.part.iss.emg.list.service.spring;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import common.bean.User;
import dream.part.iss.emg.list.dto.MaPtIssEmgDetailDTO;
import dream.part.iss.emg.list.service.MaPtIssEmgDetailService;

public abstract class AbstractMaPtIssEmgDetailService implements MaPtIssEmgDetailService {

	protected MaPtIssEmgDetailService maPtIssEmgDetailService;
	
	public AbstractMaPtIssEmgDetailService(MaPtIssEmgDetailService maPtIssEmgDetailService)
	{
		this.maPtIssEmgDetailService = maPtIssEmgDetailService;
	}
	
	public abstract String[] issueComp(MaPtIssEmgDetailDTO maPtIssEmgDetailDTO, User user)throws RemoteException, ServiceException;
	public abstract String[] issueCancel(MaPtIssEmgDetailDTO maPtIssEmgDetailDTO, User user)throws RemoteException, ServiceException;
	
}
