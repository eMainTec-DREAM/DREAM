package dream.note.daily.form;

import common.struts.BaseForm;
import dream.note.daily.dto.MaWoDailyCommonDTO;
import dream.note.daily.dto.MaWoDailyDetailDTO;
import dream.work.cal.pmrinsperiod.dto.WorkCalPmRInsPeriodCommonDTO;
import dream.work.pmi.list.dto.WorkPmiCommonDTO;

/**
 * 일일작업 - 목록 form
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maWoDailyPmiListForm"
 */
public class MaWoDailyPmiListForm extends BaseForm
{    
    //===============================================================
    /** 일일작업 공통 */
    private MaWoDailyCommonDTO maWoDailyCommonDTO = new MaWoDailyCommonDTO();
    /** 일일작업 상세 */
    private MaWoDailyDetailDTO maWoDailyDetailDTO = new MaWoDailyDetailDTO();

    /** 예방작업일정(기간) 공통 */
    private WorkCalPmRInsPeriodCommonDTO workCalPmRInsPeriodCommonDTO = new WorkCalPmRInsPeriodCommonDTO();
    /** 작업결과 공통 */ 
    private WorkPmiCommonDTO workPmiCommonDTO = new WorkPmiCommonDTO();

	public WorkCalPmRInsPeriodCommonDTO getWorkCalPmRInsPeriodCommonDTO() {
		return workCalPmRInsPeriodCommonDTO;
	}

	public void setWorkCalPmRInsPeriodCommonDTO(WorkCalPmRInsPeriodCommonDTO workCalPmRInsPeriodCommonDTO) {
		this.workCalPmRInsPeriodCommonDTO = workCalPmRInsPeriodCommonDTO;
	}

	public WorkPmiCommonDTO getWorkPmiCommonDTO() {
		return workPmiCommonDTO;
	}

	public void setWorkPmiCommonDTO(WorkPmiCommonDTO workPmiCommonDTO) {
		this.workPmiCommonDTO = workPmiCommonDTO;
	}

	public MaWoDailyDetailDTO getMaWoDailyDetailDTO()
    {
        return maWoDailyDetailDTO;
    }

    public void setMaWoDailyDetailDTO(MaWoDailyDetailDTO maWoDailyDetailDTO)
    {
        this.maWoDailyDetailDTO = maWoDailyDetailDTO;
    }

    public MaWoDailyCommonDTO getMaWoDailyCommonDTO() {
		return maWoDailyCommonDTO;
	}

	public void setMaWoDailyCommonDTO(MaWoDailyCommonDTO maWoDailyCommonDTO) {
		this.maWoDailyCommonDTO = maWoDailyCommonDTO;
	}
}
