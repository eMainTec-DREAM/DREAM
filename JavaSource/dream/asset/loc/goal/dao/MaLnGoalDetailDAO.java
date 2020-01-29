package dream.asset.loc.goal.dao;

import common.bean.User;
import dream.asset.loc.goal.dto.MaLnGoalCommonDTO;
import dream.asset.loc.goal.dto.MaLnGoalDetailDTO;

/**
 * »ó¼¼ dao
 * 
 * @author 
 * @version $Id:  $
 * @since 1.0
 */
public interface MaLnGoalDetailDAO
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
    public MaLnGoalDetailDTO findDetail(MaLnGoalCommonDTO maLnGoalCommonDTO, User loginUser);

    /**
     * detail insert
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maLnGoalDetailDTO
     * @return
     */
    public int insertDetail(MaLnGoalDetailDTO maLnGoalDetailDTO, User loginUser);
    
    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maLnGoalDetailDTO
     * @return
     */
    public int updateDetail(MaLnGoalDetailDTO maLnGoalDetailDTO, User loginUser);
}