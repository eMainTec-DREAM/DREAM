package dream.work.pm.check.service.spring;

import common.bean.User;
import dream.work.pm.check.dao.WorkPmCheckMonthlyUnitPriceDetailDAO;
import dream.work.pm.check.dto.WorkPmCheckCommonDTO;
import dream.work.pm.check.dto.WorkPmCheckMonthlyUnitPriceDetailDTO;
import dream.work.pm.check.dto.WorkPmCheckMonthlyUnitPriceListDTO;
import dream.work.pm.check.service.WorkPmCheckMonthlyUnitPriceDetailService;

/**
 * 월별단가 - Detail Service implements
 * @author youngjoo38
 * @version $Id: WorkPmCheckMonthlyUnitPriceDetailServiceImpl.java,v 1.0 2017/08/28 12:37:40 youngjoo38 Exp $
 * @since 1.0
 * @spring.bean id="workPmCheckMonthlyUnitPriceDetailServiceTarget"
 * @spring.txbn id="workPmCheckMonthlyUnitPriceDetailService"
 * @spring.property name="workPmCheckMonthlyUnitPriceDetailDAO" ref="workPmCheckMonthlyUnitPriceDetailDAO"
 */
public class WorkPmCheckMonthlyUnitPriceDetailServiceImpl implements WorkPmCheckMonthlyUnitPriceDetailService
{
    private WorkPmCheckMonthlyUnitPriceDetailDAO workPmCheckMonthlyUnitPriceDetailDAO = null;
    
    public WorkPmCheckMonthlyUnitPriceDetailDTO findDetail(WorkPmCheckCommonDTO workPmCheckCommonDTO, WorkPmCheckMonthlyUnitPriceListDTO workPmCheckMonthlyUnitPriceListDTO, User user) throws Exception
    {
    	return workPmCheckMonthlyUnitPriceDetailDAO.findDetail(workPmCheckCommonDTO,workPmCheckMonthlyUnitPriceListDTO, user);
    }
    
    public int insertDetail(WorkPmCheckCommonDTO workPmCheckCommonDTO,WorkPmCheckMonthlyUnitPriceDetailDTO workPmCheckMonthlyUnitPriceDetailDTO, User user) throws Exception
    {
     	return workPmCheckMonthlyUnitPriceDetailDAO.insertDetail(workPmCheckCommonDTO,workPmCheckMonthlyUnitPriceDetailDTO, user);
    }
    
    public int updateDetail(WorkPmCheckCommonDTO workPmCheckCommonDTO,WorkPmCheckMonthlyUnitPriceDetailDTO workPmCheckMonthlyUnitPriceDetailDTO, User user) throws Exception
    {
     	return workPmCheckMonthlyUnitPriceDetailDAO.updateDetail(workPmCheckCommonDTO,workPmCheckMonthlyUnitPriceDetailDTO, user);
    }

	public WorkPmCheckMonthlyUnitPriceDetailDAO getWorkPmCheckMonthlyUnitPriceDetailDAO() {
		return workPmCheckMonthlyUnitPriceDetailDAO;
	}

	public void setWorkPmCheckMonthlyUnitPriceDetailDAO(WorkPmCheckMonthlyUnitPriceDetailDAO workPmCheckMonthlyUnitPriceDetailDAO) {
		this.workPmCheckMonthlyUnitPriceDetailDAO = workPmCheckMonthlyUnitPriceDetailDAO;
	}
    

}
