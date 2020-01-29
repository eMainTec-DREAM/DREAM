package dream.work.rpt.basicunitanalysis.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.basicunitanalysis.dto.BasicUnitAnalysisCommonDTO;

/**
 * 원단위분석 목록
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface BasicUnitAnalysisListService
{     
    /**
     *  grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param basicUnitAnalysisCommonDTO
     * @param loginUser
     * @throws Exception
     */
    public List findList(BasicUnitAnalysisCommonDTO basicUnitAnalysisCommonDTO, User loginUser);

    public String findTotalCount(BasicUnitAnalysisCommonDTO basicUnitAnalysisCommonDTO, User user);
    
}
