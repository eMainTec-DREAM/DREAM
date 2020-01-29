package dream.tool.iss.rent.service;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import common.bean.User;
import dream.tool.iss.rent.dto.MaPttIssDetailDTO;

/**
 * 구매입고 - 상세 service
 * 
 * @author ssong
 * @version $Id:$
 * @since 1.0
 */
public interface MaPttIssDetailService
{    
    /**
     * detail find
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param ptRecListId
     * @return
     * @throws Exception
     */
    public MaPttIssDetailDTO findDetail(User user, String ptIssListId)throws Exception;
   
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttIssDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaPttIssDetailDTO maPttIssDetailDTO) throws Exception;
    
    /**
     * detail update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttIssDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaPttIssDetailDTO maPttIssDetailDTO) throws Exception;
    
    /**
     * 자재출고처리
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtIssDetailDTO
     * @param user
     * @return 
     * @throws ServiceException 
     * @throws RemoteException 
     */
    public String[] issuePart(MaPttIssDetailDTO maPttIssDetailDTO, User user) throws RemoteException, ServiceException;
    
    /**
     * 자재출고취소처리
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtIssDetailDTO
     * @param user
     * @return 
     * @throws ServiceException 
     * @throws RemoteException 
     */
    public String[] cancelIssuePart(MaPttIssDetailDTO maPttIssDetailDTO, User user) throws RemoteException, ServiceException;
}
