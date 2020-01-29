package dream.work.pm.check.form;

import common.struts.BaseForm;
import dream.work.pm.check.dto.WorkPmCheckCommonDTO;
import dream.work.pm.check.dto.WorkPmCheckMonthlyUnitPriceDetailDTO;
import dream.work.pm.check.dto.WorkPmCheckMonthlyUnitPriceListDTO;

/**
 * 월별단가 - Detail Form
 * @author youngjoo38
 * @version $Id: WorkPmCheckMonthlyUnitPriceDetailForm.java,v 1.0 2017/08/28 12:37:40 youngjoo38 Exp $
 * @since 1.0
 * @struts.form name="workPmCheckMonthlyUnitPriceDetailForm"
 */
public class WorkPmCheckMonthlyUnitPriceDetailForm extends BaseForm
{
	private WorkPmCheckCommonDTO workPmCheckCommonDTO = new WorkPmCheckCommonDTO();
	private WorkPmCheckMonthlyUnitPriceListDTO workPmCheckMonthlyUnitPriceListDTO = new WorkPmCheckMonthlyUnitPriceListDTO();
	private WorkPmCheckMonthlyUnitPriceDetailDTO workPmCheckMonthlyUnitPriceDetailDTO = new WorkPmCheckMonthlyUnitPriceDetailDTO();
	
	public WorkPmCheckCommonDTO getWorkPmCheckCommonDTO() {
		return workPmCheckCommonDTO;
	}
	public void setWorkPmCheckCommonDTO(WorkPmCheckCommonDTO workPmCheckCommonDTO) {
		this.workPmCheckCommonDTO = workPmCheckCommonDTO;
	}
	public WorkPmCheckMonthlyUnitPriceListDTO getWorkPmCheckMonthlyUnitPriceListDTO() {
		return workPmCheckMonthlyUnitPriceListDTO;
	}
	public void setWorkPmCheckMonthlyUnitPriceListDTO(WorkPmCheckMonthlyUnitPriceListDTO workPmCheckMonthlyUnitPriceListDTO) {
		this.workPmCheckMonthlyUnitPriceListDTO = workPmCheckMonthlyUnitPriceListDTO;
	}
	public WorkPmCheckMonthlyUnitPriceDetailDTO getWorkPmCheckMonthlyUnitPriceDetailDTO() {
		return workPmCheckMonthlyUnitPriceDetailDTO;
	}
	public void setWorkPmCheckMonthlyUnitPriceDetailDTO(WorkPmCheckMonthlyUnitPriceDetailDTO workPmCheckMonthlyUnitPriceDetailDTO) {
		this.workPmCheckMonthlyUnitPriceDetailDTO = workPmCheckMonthlyUnitPriceDetailDTO;
	}
}
