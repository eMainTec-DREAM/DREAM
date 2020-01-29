package dream.work.cal.pmperiod.form;

import common.struts.BaseForm;
import dream.work.cal.pmperiod.dto.MaWoSchedCommonDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * 예방작업일정(기간) - 목록 form
 * @author  kim21017
 * @version $Id: MaWoSchedListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maWoSchedListForm"
 */
public class MaWoSchedListForm extends BaseForm
{    
    //===============================================================
    /** 예방작업일정(기간) 공통 */
    private MaWoSchedCommonDTO maWoSchedCommonDTO = new MaWoSchedCommonDTO();

    /** 작업 공통 */
    private MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();

	public MaWoSchedCommonDTO getMaWoSchedCommonDTO() {
		return maWoSchedCommonDTO;
	}

	public void setMaWoSchedCommonDTO(MaWoSchedCommonDTO maWoSchedCommonDTO) {
		this.maWoSchedCommonDTO = maWoSchedCommonDTO;
	}

	public MaWoResultMstrCommonDTO getMaWoResultMstrCommonDTO() {
		return maWoResultMstrCommonDTO;
	}

	public void setMaWoResultMstrCommonDTO(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) {
		this.maWoResultMstrCommonDTO = maWoResultMstrCommonDTO;
	}

}
