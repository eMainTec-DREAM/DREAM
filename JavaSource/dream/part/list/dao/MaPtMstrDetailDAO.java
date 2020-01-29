package dream.part.list.dao;

import common.bean.User;
import dream.part.list.dto.MaPtMstrCommonDTO;
import dream.part.list.dto.MaPtMstrDetailDTO;

/**
 * ��������з�(������) - �� dao
 * 
 * @author 
 * @version $Id:  $
 * @since 1.0
 */
public interface MaPtMstrDetailDAO
{
    /**
     * detail find
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param deptNo
     * @return
     */
    public MaPtMstrDetailDTO findDetail(MaPtMstrCommonDTO maPtMstrCommonDTO, User loginUser);

    /**
     * detail insert
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPtMstrDetailDTO
     * @return
     */
    public int insertDetail(MaPtMstrDetailDTO maPtMstrDetailDTO, User loginUser);
    
    public int createStock(MaPtMstrDetailDTO maPtMstrDetailDTO,User loginUser);
    
    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPtMstrDetailDTO
     * @return
     */
    public int updateDetail(MaPtMstrDetailDTO maPtMstrDetailDTO, User loginUser);
    
    /**
     * valid check
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPtMstrDetailDTO
     * @return
     */
    public String validPartNo(MaPtMstrDetailDTO maPtMstrDetailDTO, User loginUser);

    public void SP_IF_UPD_TXPARTS(User loginUser) throws Exception;
    
    
    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPtMstrDetailDTO
     * @return
     */
    public int updateEqPartCycle(MaPtMstrDetailDTO maPtMstrDetailDTO, User loginUser);
    
}