package dream.asset.loc.goal.service;

import java.util.List;

import common.bean.User;
import dream.asset.loc.goal.dto.MaLnGoalCommonDTO;

/**
 * ��� service
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface MaLnGoalListService
{     
    /**
     *  grid find
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param maLnGoalCommonDTO
     * @param loginUser
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findList(MaLnGoalCommonDTO maLnGoalCommonDTO, User loginUser);    
   
    /**
     * delete List
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param deleteRows
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int deleteList(String[] deleteRows, User loginUser) throws Exception;
}
