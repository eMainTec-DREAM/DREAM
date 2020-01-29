package dream.work.rpt.mapmtrend.dao;

import java.util.List;

import common.bean.User;
import dream.work.rpt.mapmtrend.dto.MaPmTrendCommonDTO;

/**
 * ¸ñ·Ï dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface MaPmTrendListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPmTrendCommonDTO
     * @param loginUser
     * @return List
     */
    public List findList(MaPmTrendCommonDTO maPmTrendCommonDTO, User loginUser);

    public String findTotalCount(MaPmTrendCommonDTO maPmTrendCommonDTO, User user);
    
}