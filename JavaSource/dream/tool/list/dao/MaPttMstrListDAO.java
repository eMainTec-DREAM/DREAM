package dream.tool.list.dao;

import java.util.List;

import common.bean.User;
import dream.tool.list.dto.MaPttMstrCommonDTO;
import dream.tool.list.dto.MaPttMstrListDTO;

/**
 * 보전자재분류(마스터) - 목록 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface MaPttMstrListDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPttMstrCommonDTO
     * @return List
     */
    public List findList(MaPttMstrCommonDTO maPttMstrCommonDTO, User loginUser);

    /**
     * 삭제
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param partId
     * @return
     */
    public int updateDeletePartsFlag(MaPttMstrListDTO maPttMstrListDTO, User loginUser);
    
    public String findTotalCount(MaPttMstrCommonDTO maPttMstrCommonDTO, User user);
    
}