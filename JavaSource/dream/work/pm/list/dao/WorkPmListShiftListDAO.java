package dream.work.pm.list.dao;

import java.util.List;

import common.bean.User;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;

/**
 *  ±≥¥Î¡∂ dao
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface WorkPmListShiftListDAO
{
    /**
     * grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPmMstrCommonDTO
     * @param loginUser
     * @return List
     */
    public List findShiftList(MaPmMstrCommonDTO maPmMstrCommonDTO, User loginUser);
    
    /**
     * delete
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteShiftList(String id, String compNo);
    
    public String findTotalCount(MaPmMstrCommonDTO maPmMstrCommonDTO, User user);
}