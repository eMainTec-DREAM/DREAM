package dream.work.pm.check.form;

import common.struts.BaseForm;
import dream.work.pm.check.dto.WorkPmCheckCommonDTO;
import dream.work.pm.check.dto.WorkPmCheckMonthlyUnitPriceListDTO;

/**
 * WorkPmCheckMonthlyUnitPrice Page - List Form
 * @author youngjoo38
 * @version $Id: WorkPmCheckMonthlyUnitPriceListForm.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @struts.form name="workPmCheckMonthlyUnitPriceListForm"
 * */
public class WorkPmCheckMonthlyUnitPriceListForm extends BaseForm {
    
    private WorkPmCheckCommonDTO workPmCheckCommonDTO = new WorkPmCheckCommonDTO();
    private WorkPmCheckMonthlyUnitPriceListDTO workPmCheckMonthlyUnitPriceListDTO = new WorkPmCheckMonthlyUnitPriceListDTO();

    
    public WorkPmCheckCommonDTO getWorkPmCheckCommonDTO()
    {
        return workPmCheckCommonDTO;
    }

    public void setWorkPmCheckCommonDTO(WorkPmCheckCommonDTO workPmCheckCommonDTO)
    {
        this.workPmCheckCommonDTO = workPmCheckCommonDTO;
    }

    public WorkPmCheckMonthlyUnitPriceListDTO getWorkPmCheckMonthlyUnitPriceListDTO() {
        return workPmCheckMonthlyUnitPriceListDTO;
    }

    public void setWorkPmCheckMonthlyUnitPriceListDTO(WorkPmCheckMonthlyUnitPriceListDTO workPmCheckMonthlyUnitPriceListDTO) {
        this.workPmCheckMonthlyUnitPriceListDTO = workPmCheckMonthlyUnitPriceListDTO;
    }
}