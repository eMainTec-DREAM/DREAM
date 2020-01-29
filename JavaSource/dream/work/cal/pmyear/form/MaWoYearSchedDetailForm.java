package dream.work.cal.pmyear.form;

import common.struts.BaseForm;
import dream.work.cal.pmyear.dto.MaWoYearSchedCommonDTO;
import dream.work.cal.pmyear.dto.MaWoYearSchedDetailDTO;

/**
 * �����۾�����- �� Form
 * @author  kim21017
 * @version $Id: MaWoYearSchedDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maWoYearSchedDetailForm"
 */
public class MaWoYearSchedDetailForm extends BaseForm
{
    //========================================================================
    /** �����۾����� ���� */ 
    private MaWoYearSchedCommonDTO maWoYearSchedCommonDTO = new MaWoYearSchedCommonDTO();
    //========================================================================
    /** �����۾����� �� */
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
