package dream.tool.list.dao;

import common.bean.User;
import dream.tool.list.dto.MaPttMstrCommonDTO;
import dream.tool.list.dto.MaPttMstrDetailDTO;

/**
 * 보전자재분류(마스터) - 상세 dao
 * 
 * @author 
 * @version $Id:  $
 * @since 1.0
 */
public interface MaPttMstrDetailDAO
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
    public MaPttMstrDetailDTO findDetail(MaPttMstrCommonDTO maPttMstrCommonDTO, User loginUser);

    /**
     * detail insert
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPttMstrDetailDTO
     * @return
     */
    public int insertDetail(MaPttMstrDetailDTO maPttMstrDetailDTO, User loginUser);
    
    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPttMstrDetailDTO
     * @return
     */
    public int updateDetail(MaPttMstrDetailDTO maPttMstrDetailDTO, User loginUser);
    
    /**
     * valid check
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPttMstrDetailDTO
     * @return
     */
    public String validPartNo(MaPttMstrDetailDTO maPttMstrDetailDTO, User loginUser);
    
    public void SP_IF_UPD_TXPARTS(User loginUser) throws Exception;
}