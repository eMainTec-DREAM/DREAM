package dream.work.rpt.basicunitanalysis.dao;

import java.util.List;

import common.bean.User;
import dream.work.rpt.basicunitanalysis.dto.BasicUnitAnalysisDetailDTO;

/**
 * 원단위분석 상세 목록 dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface BasicUnitAnalysisDetailDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$   
     * @since   1.0
     * 
     * @param basicUnitAnalysisDetailDTO
     * @param loginUser
     * @return List
     */
    public List findDetail(BasicUnitAnalysisDetailDTO basicUnitAnalysisDetailDTO, User loginUser);
    
}