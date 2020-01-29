package dream.part.pur.req.service;

import common.bean.User;
import dream.part.pur.req.dto.MaPtPurReqDetailDTO;
import dream.part.pur.req.dto.MaPtReqCommonDTO;

/**
 * 何前荐府 - 惑技 service
 * 
 * @author ssong
 * @version $Id:$
 * @since 1.0
 */
public interface MaPtPurReqDetailService
{    
    /**
     * detail find
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtReqCommonDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public MaPtPurReqDetailDTO findDetail(MaPtReqCommonDTO maPtReqCommonDTO, User loginUser)throws Exception;
   
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtPurReqDetailDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int insertDetail(MaPtPurReqDetailDTO maPtPurReqDetailDTO, User loginUser) throws Exception;
    
    /**
     * detail update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtPurReqDetailDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int updateDetail(MaPtPurReqDetailDTO maPtPurReqDetailDTO, User loginUser) throws Exception;
    
    /**
     * detail 惑怕 update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtPurReqDetailDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int updateStatus(MaPtPurReqDetailDTO maPtPurReqDetailDTO, User loginUser) throws Exception;
    public int recStatus(MaPtPurReqDetailDTO maPtPurReqDetailDTO, User loginUser) throws Exception;
	public int recCancel(MaPtPurReqDetailDTO maPtPurReqDetailDTO, User loginUser);

    public MaPtPurReqDetailDTO getQty(MaPtPurReqDetailDTO maPtPurReqDetailDTO, User user) throws Exception;

    public MaPtPurReqDetailDTO getStatus(MaPtPurReqDetailDTO maPtPurReqDetailDTO, User user) throws Exception;
}
