package dream.part.iss.emg.req.service.spring;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import common.bean.User;
import dream.part.iss.emg.list.dto.MaPtIssEmgDetailDTO;
import dream.part.iss.emg.req.dto.PartIssEmgReqDetailDTO;
import dream.part.iss.emg.req.service.PartIssEmgReqDetailService;

public abstract class AbstractPartIssEmgReqDetailService implements PartIssEmgReqDetailService {

    protected PartIssEmgReqDetailService partIssEmgReqDetailService;
    
    public AbstractPartIssEmgReqDetailService(PartIssEmgReqDetailService partIssEmgReqDetailService)
    {
        this.partIssEmgReqDetailService = partIssEmgReqDetailService;
    }
    
    public abstract String[] issueParts(PartIssEmgReqDetailDTO partIssEmgReqDetailDTO, User user) throws Exception;
    public abstract String[] issueCancelParts(PartIssEmgReqDetailDTO partIssEmgReqDetailDTO, User user) throws Exception;
    
    public abstract String[] issueComp(MaPtIssEmgDetailDTO maPtIssEmgDetailDTO, User user)throws RemoteException, ServiceException;
    public abstract String[] issueCancel(MaPtIssEmgDetailDTO maPtIssEmgDetailDTO, User user)throws RemoteException, ServiceException;
}