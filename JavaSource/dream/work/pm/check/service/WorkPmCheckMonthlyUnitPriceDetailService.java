package dream.work.pm.check.service;

import common.bean.User;
import dream.work.pm.check.dto.WorkPmCheckCommonDTO;
import dream.work.pm.check.dto.WorkPmCheckMonthlyUnitPriceDetailDTO;
import dream.work.pm.check.dto.WorkPmCheckMonthlyUnitPriceListDTO;
/**
 * 월별단가 - Detail Service
 * @author youngjoo38
 * @version $Id: WorkPmCheckMonthlyUnitPriceDetailService.java,v 1.0 2017/08/28 12:37:40 youngjoo38 Exp $
 * @since 1.0
 */
public interface WorkPmCheckMonthlyUnitPriceDetailService
{    
	/**
	 * FIND DETAIL
	 * @param workPmCheckCommonDTO
	 * @param workPmCheckMonthlyUnitPriceListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public WorkPmCheckMonthlyUnitPriceDetailDTO findDetail(WorkPmCheckCommonDTO workPmCheckCommonDTO,WorkPmCheckMonthlyUnitPriceListDTO workPmCheckMonthlyUnitPriceListDTO, User user) throws Exception;
	/**
	 * INSERT DETAIL
     * @param workPmCheckCommonDTO
	 * @param workPmCheckMonthlyUnitPriceDetailDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public int insertDetail(WorkPmCheckCommonDTO workPmCheckCommonDTO,WorkPmCheckMonthlyUnitPriceDetailDTO workPmCheckMonthlyUnitPriceDetailDTO, User user) throws Exception;
    /**
     * UPDATE DETAIL
     * @param workPmCheckCommonDTO
     * @param workPmCheckMonthlyUnitPriceDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(WorkPmCheckCommonDTO workPmCheckCommonDTO,WorkPmCheckMonthlyUnitPriceDetailDTO workPmCheckMonthlyUnitPriceDetailDTO, User user) throws Exception;
    
}
