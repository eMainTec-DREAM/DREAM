package dream.tool.adj.service;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import common.bean.User;
import dream.tool.adj.dto.MaPttDisDetailDTO;

/**
 * 공기구반납 - 상세 service
 * 
 * @author ssong
 * @version $Id:$
 * @since 1.0
 */
public interface MaPttDisDetailService
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
    public MaPttDisDetailDTO findDetail(User user, String ptIssListId)throws Exception;
   
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttDisDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaPttDisDetailDTO maPttDisDetailDTO) throws Exception;
    
    /**
     * detail update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttDisDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaPttDisDetailDTO maPttDisDetailDTO) throws Exception;
    
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
     * @throws Exception 
     */
    public String[] disPart(MaPttDisDetailDTO maPttDisDetailDTO, User user) throws RemoteException, ServiceException, Exception;
    
}
