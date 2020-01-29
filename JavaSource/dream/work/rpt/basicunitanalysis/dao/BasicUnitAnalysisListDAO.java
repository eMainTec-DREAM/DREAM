package dream.work.rpt.basicunitanalysis.dao;

import java.util.List;

import common.bean.User;
import dream.work.rpt.basicunitanalysis.dto.BasicUnitAnalysisCommonDTO;

/**
 * 원단위분석 목록 dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface BasicUnitAnalysisListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param basicUnitAnalysisCommonDTO
     * @param loginUser
     * @return List
     */
    public List findList(BasicUnitAnalysisCommonDTO basicUnitAnalysisCommonDTO, User loginUser);

    public String findTotalCount(BasicUnitAnalysisCommonDTO basicUnitAnalysisCommonDTO, User user);
    
}