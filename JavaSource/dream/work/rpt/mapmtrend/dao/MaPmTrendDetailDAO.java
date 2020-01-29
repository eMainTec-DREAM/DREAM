package dream.work.rpt.mapmtrend.dao;

import java.util.List;

import common.bean.User;
import dream.work.rpt.mapmtrend.dto.MaPmTrendCommonDTO;
import dream.work.rpt.mapmtrend.dto.MaPmTrendDetailDTO;

/**
 * 상세 목록 dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface MaPmTrendDetailDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$   
     * @since   1.0
     * 
     * @param maPmTrendDetailDTO
     * @param loginUser
     * @return List
     */
    public List findDetail(MaPmTrendCommonDTO maPmTrendCommonDTO, MaPmTrendDetailDTO maPmTrendDetailDTO, User loginUser);
    
    public int createWo(MaPmTrendDetailDTO maPmTrendDetailDTO, User loginUser);

}