package dream.tool.stk.service;

import java.util.List;

import common.bean.User;
import dream.tool.stk.dto.MaPttStckCommonDTO;
import dream.tool.stk.dto.MaPttStckDetailDTO;

/**
 * 자재재고 - 상세 service
 * 
 * @author ssong
 * @version $Id:$
 * @since 1.0
 */
public interface MaPttStckDetailService
{    
    /**
     * detail find
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttStckCommonDTO
     * @return
     * @throws Exception
     */
    public MaPttStckDetailDTO findDetail(MaPttStckCommonDTO maPttStckCommonDTO)throws Exception;
   
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttStckDetailDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int insertDetail(MaPttStckDetailDTO maPttStckDetailDTO, User loginUser) throws Exception;
    
    /**
     * detail update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttStckDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaPttStckDetailDTO maPttStckDetailDTO, User loginUser) throws Exception;
    
    /**
     * valid Pk
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPttStckDetailDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public String validPtStck(MaPttStckDetailDTO maPttStckDetailDTO) throws Exception;
}
