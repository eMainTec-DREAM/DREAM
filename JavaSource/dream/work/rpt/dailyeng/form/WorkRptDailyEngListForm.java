package dream.work.rpt.dailyeng.form;

import common.struts.BaseForm;
import dream.work.rpt.dailyeng.dto.WorkRptDailyEngCommonDTO;
import dream.work.rpt.dailyeng.dto.WorkRptDailyEngDetailListDTO;

/**
 * 에너지사용량(일별)
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="workRptDailyEngListForm"
 */
public class WorkRptDailyEngListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
private WorkRptDailyEngCommonDTO workRptDailyEngCommonDTO = new WorkRptDailyEngCommonDTO();
    
    private WorkRptDailyEngDetailListDTO workRptDailyEngDetailListDTO = new WorkRptDailyEngDetailListDTO();
    
    public WorkRptDailyEngCommonDTO getWorkRptDailyEngCommonDTO()
    {
        return workRptDailyEngCommonDTO;
    }

    public void setWorkRptDailyEngCommonDTO(
            WorkRptDailyEngCommonDTO workRptDailyEngCommonDTO)
    {
        this.workRptDailyEngCommonDTO = workRptDailyEngCommonDTO;
    }
    
    public WorkRptDailyEngDetailListDTO getWorkRptDailyEngDetailListDTO()
    {
        return workRptDailyEngDetailListDTO;
    }

    public void setWorkRptDailyEngDetailListDTO(
            WorkRptDailyEngDetailListDTO workRptDailyEngDetailListDTO)
    {
        this.workRptDailyEngDetailListDTO = workRptDailyEngDetailListDTO;
    }
	
}
