package dream.consult.program.wrkimp.dao;

import java.util.List;

import common.bean.User;
import dream.consult.program.wrkimp.dto.MaWrkimpCommonDTO;

/**
 * 사원 - 목록 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface MaWrkimpListDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maWrkimpCommonDTO
     * @return List
     */
    public List findWrkimpList(MaWrkimpCommonDTO maWrkimpCommonDTO, User user);
    public String findTotalCount(MaWrkimpCommonDTO maWrkimpCommonDTO, User user);
    
    /**
     * 삭제
     * @author ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param empId
     * @return
     */
    public int deleteWrkimp(String gowrkimp, User user);
}