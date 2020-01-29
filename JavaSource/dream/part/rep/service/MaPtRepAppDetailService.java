package dream.part.rep.service;

import common.bean.User;
import dream.part.rep.dto.MaPtRepAppDetailDTO;
import dream.part.rep.dto.MaPtRepCommonDTO;

/**
 * 수리기안 - 상세 service
 * 
 * @author ssong
 * @version $Id:$
 * @since 1.0
 */
public interface MaPtRepAppDetailService
{    
    /**
     * detail find
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRepCommonDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public MaPtRepAppDetailDTO findDetail(MaPtRepCommonDTO maPtRepCommonDTO, User loginUser)throws Exception;
   
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRepAppDetailDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int insertDetail(MaPtRepAppDetailDTO maPtRepAppDetailDTO, User loginUser) throws Exception;
    
    /**
     * detail update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRepAppDetailDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int updateDetail(MaPtRepAppDetailDTO maPtRepAppDetailDTO, User loginUser) throws Exception;
    
}