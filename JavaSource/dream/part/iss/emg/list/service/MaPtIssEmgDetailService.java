package dream.part.iss.emg.list.service;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import common.bean.User;
import dream.part.iss.emg.list.dto.MaPtIssEmgCommonDTO;
import dream.part.iss.emg.list.dto.MaPtIssEmgDetailDTO;


/**
 * 긴급출고 - 상세 service
 * 
 * @author ssong
 * @version $Id:$
 * @since 1.0
 */
public interface MaPtIssEmgDetailService
{    
    /**
     * detail find
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtIssEmgCommonDTO
     * @param user 
     * @return
     * @throws Exception
     */
    public MaPtIssEmgDetailDTO findDetail(MaPtIssEmgCommonDTO maPtIssEmgCommonDTO, User user)throws Exception;
   
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtIssEmgDetailDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int insertDetail(MaPtIssEmgDetailDTO maPtIssEmgDetailDTO, User loginUser) throws Exception;
    
    /**
     * detail update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtIssEmgDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaPtIssEmgDetailDTO maPtIssEmgDetailDTO, User loginUser) throws Exception;

    /**
     * 자재출고처리
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtIssEmgDetailDTO
     * @param user
     * @return 
     * @throws ServiceException 
     * @throws RemoteException 
     */
    public String[] issueComp(MaPtIssEmgDetailDTO maPtIssEmgDetailDTO, User user) throws RemoteException, ServiceException;

    /**
     * 자재출고취소
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtIssEmgDetailDTO
     * @param user
     * @return 
     * @throws ServiceException 
     * @throws RemoteException 
     */
    public String[] issueCancel(MaPtIssEmgDetailDTO maPtIssEmgDetailDTO, User user) throws RemoteException, ServiceException;

    public String findStockQty(MaPtIssEmgDetailDTO maPtIssEmgDetailDTO, User user) throws Exception;


}
