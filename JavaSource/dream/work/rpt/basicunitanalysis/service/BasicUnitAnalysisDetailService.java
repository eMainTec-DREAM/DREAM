package dream.work.rpt.basicunitanalysis.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.basicunitanalysis.dto.BasicUnitAnalysisDetailDTO;

/**
 * �������м� �� ���
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface BasicUnitAnalysisDetailService
{     
    /**
     *  grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param basicUnitAnalysisDetailDTO
     * @param loginUser
     * @throws Exception
     */
    public List findDetail(BasicUnitAnalysisDetailDTO basicUnitAnalysisDetailDTO, User loginUser);
    
}
