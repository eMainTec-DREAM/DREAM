package dream.work.cal.womonth.form;

import common.struts.BaseForm;
import dream.work.cal.womonth.dto.MaWoMonthWoCommonDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * 월간작업일정 - 목록 form
 * @author  kim21017
 * @version $Id: MaWoMonthWoListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maWoMonthWoListForm"
 */
public class MaWoMonthWoListForm extends BaseForm
{    
    //===============================================================
    /** 월간작업일정 공통 */
    private MaWoMonthWoCommonDTO maWoMonthWoCommonDTO = new MaWoMonthWoCommonDTO();
    private MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();
	public MaWoMonthWoCommonDTO getMaWoMonthWoCommonDTO() {
		return maWoMonthWoCommonDTO;
	}

	public void setMaWoMonthWoCommonDTO(MaWoMonthWoCommonDTO maWoMonthWoCommonDTO) {
		this.maWoMonthWoCommonDTO = maWoMonthWoCommonDTO;
	}

	public MaWoResultMstrCommonDTO getMaWoResultMstrCommonDTO() {
		return maWoResultMstrCommonDTO;
	}

	public void setMaWoResultMstrCommonDTO(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) {
		this.maWoResultMstrCommonDTO = maWoResultMstrCommonDTO;
	}
	

}
