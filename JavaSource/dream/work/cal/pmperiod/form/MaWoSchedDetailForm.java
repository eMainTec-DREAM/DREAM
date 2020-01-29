package dream.work.cal.pmperiod.form;

import common.struts.BaseForm;
import dream.work.cal.pmperiod.dto.MaWoSchedCommonDTO;
import dream.work.cal.pmperiod.dto.MaWoSchedDetailDTO;

/**
 * 예방작업일정(기간)- 상세 Form
 * @author  kim21017
 * @version $Id: MaWoSchedDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maWoSchedDetailForm"
 */
public class MaWoSchedDetailForm extends BaseForm
{
    //========================================================================
    /** 예방작업일정(기간) 공통 */ 
    private MaWoSchedCommonDTO maWoSchedCommonDTO = new MaWoSchedCommonDTO();
    //========================================================================
    /** 예방작업일정(기간) 상세 */
    private MaWoSchedDetailDTO maWoSchedDetailDTO = new MaWoSchedDetailDTO();
    

	public MaWoSchedCommonDTO getMaWoSchedCommonDTO() {
		return maWoSchedCommonDTO;
	}

	public void setMaWoSchedCommonDTO(MaWoSchedCommonDTO maWoSchedCommonDTO) {
		this.maWoSchedCommonDTO = maWoSchedCommonDTO;
	}

	public MaWoSchedDetailDTO getMaWoSchedDetailDTO() {
		return maWoSchedDetailDTO;
	}

	public void setMaWoSchedDetailDTO(MaWoSchedDetailDTO maWoSchedDetailDTO) {
		this.maWoSchedDetailDTO = maWoSchedDetailDTO;
	}

}
