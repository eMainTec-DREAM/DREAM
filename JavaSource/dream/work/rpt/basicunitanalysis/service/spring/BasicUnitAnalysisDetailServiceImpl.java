package dream.work.rpt.basicunitanalysis.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.rpt.basicunitanalysis.dao.BasicUnitAnalysisDetailDAO;
import dream.work.rpt.basicunitanalysis.dto.BasicUnitAnalysisDetailDTO;
import dream.work.rpt.basicunitanalysis.service.BasicUnitAnalysisDetailService;

/**
 * 원단위분석 상세 목록
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="basicUnitAnalysisDetailServiceTarget"
 * @spring.txbn id="basicUnitAnalysisDetailService"
 * @spring.property name="basicUnitAnalysisDetailDAO" ref="basicUnitAnalysisDetailDAO"
 */
public class BasicUnitAnalysisDetailServiceImpl implements BasicUnitAnalysisDetailService
{
    private BasicUnitAnalysisDetailDAO basicUnitAnalysisDetailDAO = null;
    
    public BasicUnitAnalysisDetailDAO getBasicUnitAnalysisDetailDAO()
    {
        return basicUnitAnalysisDetailDAO;
    }
    
    public void setBasicUnitAnalysisDetailDAO(
            BasicUnitAnalysisDetailDAO basicUnitAnalysisDetailDAO)
    {
        this.basicUnitAnalysisDetailDAO = basicUnitAnalysisDetailDAO;
    }
    
    public List findDetail(BasicUnitAnalysisDetailDTO basicUnitAnalysisDetailDTO, User loginUser)
    {
        return basicUnitAnalysisDetailDAO.findDetail(basicUnitAnalysisDetailDTO, loginUser);
        
    }
	
}

