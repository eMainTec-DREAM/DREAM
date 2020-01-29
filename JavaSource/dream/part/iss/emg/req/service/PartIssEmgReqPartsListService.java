package dream.part.iss.emg.req.service;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import common.bean.User;

import dream.part.iss.emg.req.dto.PartIssEmgReqCommonDTO;
import dream.part.iss.emg.req.dto.PartIssEmgReqPartsDetailDTO;

/**
 * 긴급출고 - 목록 service
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface PartIssEmgReqPartsListService
{     
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @param maPtIssEmgCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws ServiceException 
     * @throws RemoteException 
     * @throws Exception
     */
    public List findPtIssEmgList(PartIssEmgReqCommonDTO partIssEmgReqCommonDTO,User user) throws RemoteException, ServiceException;

    public int deleteKey(String[] deleteRows, User user);

    public String findTotalCount(PartIssEmgReqCommonDTO partIssEmgReqCommonDTO, User user);

    public int insertPtIssEmgList(PartIssEmgReqPartsDetailDTO partIssEmgReqPartsDetailDTO, User user) throws Exception;

    public void saveList(List<Map> gridList, User user) throws Exception;


}
