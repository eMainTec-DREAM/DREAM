package dream.work.cal.pmyear.form;

import common.struts.BaseForm;
import dream.work.cal.pmyear.dto.MaWoYearSchedCommonDTO;
import dream.work.cal.pmyear.dto.MaWoYearSchedDetailDTO;

/**
 * 연간작업일정- 상세 Form
 * @author  kim21017
 * @version $Id: MaWoYearSchedDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maWoYearSchedDetailForm"
 */
public class MaWoYearSchedDetailForm extends BaseForm
{
    //========================================================================
    /** 연간작업일정 공통 */ 
    private MaWoYearSchedCommonDTO maWoYearSchedCommonDTO = new MaWoYearSchedCommonDTO();
    //========================================================================
    /** 연간작업일정 상세 */
    private MaWoYearSchedDetailDTO maWoYearSchedDetailDTO = new MaWoYearSchedDetailDTO();
    

	public MaWoYearSchedCommonDTO getMaWoYearSchedCommonDTO() {
		return maWoYearSchedCommonDTO;
	}

	public void setMaWoYearSchedCommonDTO(MaWoYearSchedCommonDTO maWoYearSchedCommonDTO) {
		this.maWoYearSchedCommonDTO = maWoYearSchedCommonDTO;
	}

	public MaWoYearSchedDetailDTO getMaWoYearSchedDetailDTO() {
		return maWoYearSchedDetailDTO;
	}

	public void setMaWoYearSchedDetailDTO(MaWoYearSchedDetailDTO maWoYearSchedDetailDTO) {
		this.maWoYearSchedDetailDTO = maWoYearSchedDetailDTO;
	}

}
