package dream.asset.loc.goal.dao;

import java.util.List;

import common.bean.User;
import dream.asset.loc.goal.dto.MaLnGoalCommonDTO;

/**
 * ��� dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface MaLnGoalListDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maLnGoalCommonDTO
     * @return List
     */
    public List findList(MaLnGoalCommonDTO maLnGoalCommonDTO, User loginUser);
    

    /**
     * ����
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param partId
     * @return
     */
    public int deleteHeader(String key, User loginUser);
}