package dream.asset.loc.goal.service;

import java.util.List;

import common.bean.User;
import dream.asset.loc.goal.dto.MaLnGoalCommonDTO;
import dream.asset.loc.goal.dto.MaLnGoalDetailDTO;


/**
 *  »ó¼¼ service
 * 
 * @author 
 * @version $Id:$
 * @since 1.0
 */
public interface MaLnGoalDetailService
{    
    /**
     * detail find
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param maLnGoalCommonDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public MaLnGoalDetailDTO findDetail(MaLnGoalCommonDTO maLnGoalCommonDTO, User loginUser);
    
    /**
     * detail insert
     * @author 
     * @version $Id:$
     * @since   1.0
     * 
     * @param maLnGoalDetailDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int insertDetail(MaLnGoalDetailDTO maLnGoalDetailDTO, User loginUser) throws Exception;
    
    /**
     * detail update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maLnGoalDetailDTO
     * @param loginUser
     * @return
     * @throws Exception
     */
    public int updateDetail(MaLnGoalDetailDTO maLnGoalDetailDTO, User loginUser) throws Exception;

}
