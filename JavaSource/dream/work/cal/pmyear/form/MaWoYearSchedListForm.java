package dream.work.cal.pmyear.form;

import common.struts.BaseForm;
import dream.work.cal.pmyear.dto.MaWoYearSchedCommonDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * 연간작업일정 - 목록 form
 * @author  kim21017
 * @version $Id: MaWoYearSchedListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maWoYearSchedListForm"
 */
public class MaWoYearSchedListForm extends BaseForm
{    
    //===============================================================
    /** 연간작업일정 공통 */
    private MaWoYearSchedCommonDTO maWoYearSchedCommonDTO = new MaWoYearSchedCommonDTO();

    /** 작업 공통 */
    private MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();

    
	public MaWoYearSchedCommonDTO getMaWoYearSchedCommonDTO() {
		return maWoYearSchedCommonDTO;
	}

	public void setMaWoYearSchedCommonDTO(MaWoYearSchedCommonDTO maWoYearSchedCommonDTO) {
		this.maWoYearSchedCommonDTO = maWoYearSchedCommonDTO;
	}

	public MaWoResultMstrCommonDTO getMaWoResultMstrCommonDTO() {
		return maWoResultMstrCommonDTO;
	}

	public void setMaWoResultMstrCommonDTO(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) {
		this.maWoResultMstrCommonDTO = maWoResultMstrCommonDTO;
	}
	

}
