package dream.work.rpt.mapmtrend.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.mapmtrend.dto.MaPmTrendCommonDTO;

/**
 * ¸ñ·Ï
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface MaPmTrendListService
{     
    /**
     *  grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPmTrendCommonDTO
     * @param loginUser
     * @throws Exception
     */
    public List findList(MaPmTrendCommonDTO maPmTrendCommonDTO, User loginUser);

    public String findTotalCount(MaPmTrendCommonDTO maPmTrendCommonDTO, User user);
    
}
