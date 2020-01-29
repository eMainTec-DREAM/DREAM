package dream.tool.iss.rtn.service;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import common.bean.User;
import dream.tool.iss.rtn.dto.MaPttRtnDetailDTO;

/**
 * 공기구반납 - 상세 service
 * 
 * @author ssong
 * @version $Id:$
 * @since 1.0
 */
public interface MaPttRtnDetailService
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
    public MaPttRtnDetailDTO findDetail(User user, String ptIssListId)throws Exception;
   
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttRtnDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaPttRtnDetailDTO maPttRtnDetailDTO) throws Exception;
    
    /**
     * detail update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttRtnDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaPttRtnDetailDTO maPttRtnDetailDTO) throws Exception;
    
    /**
     * 자재반납처리
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
    public String[] rtnPart(MaPttRtnDetailDTO maPttRtnDetailDTO, User user) throws RemoteException, ServiceException;
    
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
    public String[] cancelIssuePart(MaPttRtnDetailDTO maPttRtnDetailDTO, User user) throws RemoteException, ServiceException;
}
