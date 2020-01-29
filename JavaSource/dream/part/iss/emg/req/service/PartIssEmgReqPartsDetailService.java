package dream.part.iss.emg.req.service;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import common.bean.User;

import dream.part.iss.emg.req.dto.PartIssEmgReqCommonDTO;
import dream.part.iss.emg.req.dto.PartIssEmgReqPartsDetailDTO;


/**
 * 긴급출고 - 상세 service
 * 
 * @author ssong
 * @version $Id:$
 * @since 1.0
 */
public interface PartIssEmgReqPartsDetailService
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
    public PartIssEmgReqPartsDetailDTO findDetail(PartIssEmgReqCommonDTO partIssEmgReqCommonDTO, User user)throws Exception;
   
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
    public int insertDetail(PartIssEmgReqPartsDetailDTO partIssEmgReqPartsDetailDTO, User user) throws Exception;
    
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
    public int updateDetail(PartIssEmgReqPartsDetailDTO partIssEmgReqPartsDetailDTO, User user) throws Exception;

}
