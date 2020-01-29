package dream.work.pm.list.service;

import java.util.List;

import common.bean.User;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;

/**
 * 교대조 목록
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public interface WorkPmListShiftListService
{     
    /**
     *  grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPmMstrCommonDTO
     * @param loginUser
     * @throws Exception
     */
    public List findShiftList(MaPmMstrCommonDTO maPmMstrCommonDTO, User loginUser);
    /**
     *  delete
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param deleteRows
     * @param compNo
     * @throws Exception
     */
    public int deleteShiftList(String[] deleteRows, String compNo) throws Exception;
    
    public String findTotalCount(MaPmMstrCommonDTO maPmMstrCommonDTO, User user);
}
