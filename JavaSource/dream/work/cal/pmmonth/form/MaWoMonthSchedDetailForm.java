package dream.work.cal.pmmonth.form;

import common.struts.BaseForm;
import dream.work.cal.pmmonth.dto.MaWoMonthSchedCommonDTO;
import dream.work.cal.pmmonth.dto.MaWoMonthSchedDetailDTO;

/**
 * ������������- �� Form
 * @author  kim21017
 * @version $Id: MaWoMonthSchedDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maWoMonthSchedDetailForm"
 */
public class MaWoMonthSchedDetailForm extends BaseForm
{
    //========================================================================
    /** ������������ ���� */ 
    private MaWoMonthSchedCommonDTO maWoMonthSchedCommonDTO = new MaWoMonthSchedCommonDTO();
    //========================================================================
    /** ������������ �� */
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
