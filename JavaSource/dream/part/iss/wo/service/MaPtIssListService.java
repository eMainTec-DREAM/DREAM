package dream.part.iss.wo.service;

import java.rmi.RemoteException;
import java.util.List;

import javax.xml.rpc.ServiceException;

import common.bean.User;
import dream.part.iss.wo.dto.MaPtIssCommonDTO;
import dream.part.iss.wo.dto.MaPtIssDetailDTO;
import dream.part.list.dto.MaPtMstrCommonDTO;

/**
 * 자재출고확정 - 목록 service
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface MaPtIssListService
{     
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @param maPtIssCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws ServiceException 
     * @throws RemoteException 
     * @throws Exception
     */
    public List findPtIssList(MaPtIssCommonDTO maPtIssCommonDTO,User user) throws RemoteException, ServiceException;
    public String findTotalCount(MaPtIssCommonDTO maPtIssCommonDTO, User user);
    
    public int deleteKey(String[] deleteRows, User user);
    public int insertPtIssList(MaPtIssDetailDTO maPtIssDetailDTO, User user) throws Exception;


}
