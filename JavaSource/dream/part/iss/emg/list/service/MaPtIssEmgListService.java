package dream.part.iss.emg.list.service;

import java.rmi.RemoteException;
import java.util.List;

import javax.xml.rpc.ServiceException;

import common.bean.User;
import dream.part.iss.emg.list.dto.MaPtIssEmgCommonDTO;
import dream.part.iss.emg.list.dto.MaPtIssEmgDetailDTO;

/**
 * 긴급출고 - 목록 service
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaPtIssEmgListService
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
    public List findPtIssEmgList(MaPtIssEmgCommonDTO maPtIssEmgCommonDTO,User user) throws RemoteException, ServiceException;

    public int deleteKey(String[] deleteRows, User user);

    public String findTotalCount(MaPtIssEmgCommonDTO maPtIssEmgCommonDTO, User user);

    public int insertPtIssEmgList(MaPtIssEmgDetailDTO maPtIssEmgDetailDTO, User user) throws Exception;


}
