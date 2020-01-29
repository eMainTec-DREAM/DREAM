package dream.consult.comp.warehouse.service;

import common.bean.User;
import dream.consult.comp.warehouse.dto.MaPtWhCommonDTO;
import dream.consult.comp.warehouse.dto.MaPtWhDetailDTO;

/**
 * 부품창고 - 상세 service
 * 
 * @author ssong
 * @version $Id:$
 * @since 1.0
 */
public interface MaPtWhDetailService
{    
    /**
     * detail find
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtWhCommonDTO
     * @return
     * @throws Exception
     */
    public MaPtWhDetailDTO findDetail(MaPtWhCommonDTO maPtWhCommonDTO, User user)throws Exception;
   
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtWhDetailDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int insertDetail(MaPtWhDetailDTO maPtWhDetailDTO, User loginUser) throws Exception;
    
    /**
     * detail update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtWhDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaPtWhDetailDTO maPtWhDetailDTO, User loginUser) throws Exception;
}
