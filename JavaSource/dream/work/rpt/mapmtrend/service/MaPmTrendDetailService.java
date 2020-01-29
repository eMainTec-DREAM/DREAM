package dream.work.rpt.mapmtrend.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.mapmtrend.dto.MaPmTrendCommonDTO;
import dream.work.rpt.mapmtrend.dto.MaPmTrendDetailDTO;

/**
 * ¸ñ·Ï
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface MaPmTrendDetailService
{     
    /**
     *  grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPmTrendDetailDTO
     * @param loginUser
     * @throws Exception
     */
    public List findDetail(MaPmTrendCommonDTO maPmTrendCommonDTO, MaPmTrendDetailDTO maPmTrendDetailDTO, User loginUser);
    
    public int createWo(MaPmTrendDetailDTO maPmTrendDetailDTO, User loginUser);
}
