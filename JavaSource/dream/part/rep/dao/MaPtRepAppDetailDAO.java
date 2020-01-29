package dream.part.rep.dao;

import common.bean.User;
import dream.part.rep.dto.MaPtRepAppDetailDTO;
import dream.part.rep.dto.MaPtRepCommonDTO;

/**
 * 수리기안 - 상세 dao
 * 
 * @author ssong
 * @version $Id: $
 * @since 1.0
 */
public interface MaPtRepAppDetailDAO
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
     */
    public MaPtRepAppDetailDTO findDetail(MaPtRepCommonDTO maPtRepCommonDTO, User loginUser);
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRepAppDetailDTO
     * @return
     */
    public int insertDetail(MaPtRepAppDetailDTO maPtRepAppDetailDTO, User loginUser);
    
    /**
     * detail 
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtRepAppDetailDTO
     * @return
     */
    public int updateDetail(MaPtRepAppDetailDTO maPtRepAppDetailDTO, User loginUser);
}