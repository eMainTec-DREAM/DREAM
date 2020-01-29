package dream.part.stk.service;

import java.util.List;

import common.bean.User;
import dream.part.stk.dto.MaPtStckCommonDTO;
import dream.part.stk.dto.MaPtStckDetailDTO;

/**
 * 자재재고 - 상세 service
 * 
 * @author ssong
 * @version $Id:$
 * @since 1.0
 */
public interface MaPtStckDetailService
{    
    /**
     * detail find
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtStckCommonDTO
     * @return
     * @throws Exception
     */
    public MaPtStckDetailDTO findDetail(MaPtStckCommonDTO maPtStckCommonDTO)throws Exception;
   
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtStckDetailDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int insertDetail(MaPtStckDetailDTO maPtStckDetailDTO, User loginUser) throws Exception;
    
    /**
     * detail update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtStckDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaPtStckDetailDTO maPtStckDetailDTO, User loginUser) throws Exception;
    
    /**
     * valid Pk
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtStckDetailDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public String validPtStck(MaPtStckDetailDTO maPtStckDetailDTO) throws Exception;
    public List getReportView(MaPtStckDetailDTO maPtStckDetailDTO,User user);

}
