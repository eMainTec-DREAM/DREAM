package dream.work.cal.pmperiod.form;

import common.struts.BaseForm;
import dream.work.cal.pmperiod.dto.MaWoSchedCommonDTO;
import dream.work.cal.pmperiod.dto.MaWoSchedDetailDTO;

/**
 * �����۾�����(�Ⱓ)- �� Form
 * @author  kim21017
 * @version $Id: MaWoSchedDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maWoSchedDetailForm"
 */
public class MaWoSchedDetailForm extends BaseForm
{
    //========================================================================
    /** �����۾�����(�Ⱓ) ���� */ 
    private MaWoSchedCommonDTO maWoSchedCommonDTO = new MaWoSchedCommonDTO();
    //========================================================================
    /** �����۾�����(�Ⱓ) �� */
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
