package dream.work.rpt.basicunitanalysis.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.rpt.basicunitanalysis.dao.BasicUnitAnalysisListDAO;
import dream.work.rpt.basicunitanalysis.dto.BasicUnitAnalysisCommonDTO;
import dream.work.rpt.basicunitanalysis.service.BasicUnitAnalysisListService;

/**
 * 원단위분석 목록
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="basicUnitAnalysisListServiceTarget"
 * @spring.txbn id="basicUnitAnalysisListService"
 * @spring.property name="basicUnitAnalysisListDAO" ref="basicUnitAnalysisListDAO"
 */
public class BasicUnitAnalysisListServiceImpl implements BasicUnitAnalysisListService
{
    private BasicUnitAnalysisListDAO basicUnitAnalysisListDAO = null;
    
	public BasicUnitAnalysisListDAO getBasicUnitAnalysisListDAO()
    {
        return basicUnitAnalysisListDAO;
    }
	
    public void setBasicUnitAnalysisListDAO(
            BasicUnitAnalysisListDAO basicUnitAnalysisListDAO)
    {
        this.basicUnitAnalysisListDAO = basicUnitAnalysisListDAO;
    }
    
    public List findList(BasicUnitAnalysisCommonDTO basicUnitAnalysisCommonDTO, User loginUser)
    {
        return basicUnitAnalysisListDAO.findList(basicUnitAnalysisCommonDTO, loginUser);
    }

    @Override
    public String findTotalCount(BasicUnitAnalysisCommonDTO basicUnitAnalysisCommonDTO, User user)
    {
        return basicUnitAnalysisListDAO.findTotalCount(basicUnitAnalysisCommonDTO, user);
    }
	
}

