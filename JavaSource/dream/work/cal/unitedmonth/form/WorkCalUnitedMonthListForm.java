package dream.work.cal.unitedmonth.form;

import common.struts.BaseForm;
import dream.work.cal.unitedmonth.dto.WorkCalUnitedMonthCommonDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.pmi.list.dto.WorkPmiCommonDTO;

/**
 * 월간작업일정 - 목록 form
 * @author  youngjoo38
 * @version $Id: WorkCalUnitedMonthListForm.java,v 1.0 2015/12/01 09:13:09 youngjoo38 Exp $
 * @since   1.0
 *
 * @struts.form name="workCalUnitedMonthListForm"
 */
public class WorkCalUnitedMonthListForm extends BaseForm
{    
    //===============================================================
    /** 월간작업일정 공통 */
    private WorkCalUnitedMonthCommonDTO workCalUnitedMonthCommonDTO = new WorkCalUnitedMonthCommonDTO();
    private MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();
    private WorkPmiCommonDTO workPmiCommonDTO = new WorkPmiCommonDTO();
    
	public WorkPmiCommonDTO getWorkPmiCommonDTO() {
		return workPmiCommonDTO;
	}

	public void setWorkPmiCommonDTO(WorkPmiCommonDTO workPmiCommonDTO) {
		this.workPmiCommonDTO = workPmiCommonDTO;
	}

	public WorkCalUnitedMonthCommonDTO getWorkCalUnitedMonthCommonDTO() {
		return workCalUnitedMonthCommonDTO;
	}

	public void setWorkCalUnitedMonthCommonDTO(WorkCalUnitedMonthCommonDTO workCalUnitedMonthCommonDTO) {
		this.workCalUnitedMonthCommonDTO = workCalUnitedMonthCommonDTO;
	}

	public MaWoResultMstrCommonDTO getMaWoResultMstrCommonDTO() {
		return maWoResultMstrCommonDTO;
	}

	public void setMaWoResultMstrCommonDTO(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) {
		this.maWoResultMstrCommonDTO = maWoResultMstrCommonDTO;
	}
	

}
