package dream.work.cal.pmmonth.form;

import common.struts.BaseForm;
import dream.work.cal.pmmonth.dto.MaWoMonthSchedCommonDTO;
import dream.work.cal.pmmonth.dto.MaWoMonthSchedDetailDTO;

/**
 * 월간예방일정- 상세 Form
 * @author  kim21017
 * @version $Id: MaWoMonthSchedDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maWoMonthSchedDetailForm"
 */
public class MaWoMonthSchedDetailForm extends BaseForm
{
    //========================================================================
    /** 월간예방일정 공통 */ 
    private MaWoMonthSchedCommonDTO maWoMonthSchedCommonDTO = new MaWoMonthSchedCommonDTO();
    //========================================================================
    /** 월간예방일정 상세 */
    private MaWoMonthSchedDetailDTO maWoMonthSchedDetailDTO = new MaWoMonthSchedDetailDTO();
    

	public MaWoMonthSchedCommonDTO getMaWoMonthSchedCommonDTO() {
		return maWoMonthSchedCommonDTO;
	}

	public void setMaWoMonthSchedCommonDTO(MaWoMonthSchedCommonDTO maWoMonthSchedCommonDTO) {
		this.maWoMonthSchedCommonDTO = maWoMonthSchedCommonDTO;
	}

	public MaWoMonthSchedDetailDTO getMaWoMonthSchedDetailDTO() {
		return maWoMonthSchedDetailDTO;
	}

	public void setMaWoMonthSchedDetailDTO(MaWoMonthSchedDetailDTO maWoMonthSchedDetailDTO) {
		this.maWoMonthSchedDetailDTO = maWoMonthSchedDetailDTO;
	}

}
