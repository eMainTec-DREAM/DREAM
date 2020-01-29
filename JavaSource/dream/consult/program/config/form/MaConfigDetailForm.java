package dream.consult.program.config.form;

import common.struts.BaseForm;
import dream.consult.program.config.dto.MaConfigCommonDTO;
import dream.consult.program.config.dto.MaConfigDetailDTO;

/**
 * �ý��� ȯ�溯��- �� Form
 * @author  kim21017
 * @version $Id: MaConfigDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maConfigDetailForm"
 */
public class MaConfigDetailForm extends BaseForm
{
    //========================================================================
    /** �ý���ȯ�溯�� ���� */ 
    private MaConfigCommonDTO maConfigCommonDTO = new MaConfigCommonDTO();
    //========================================================================
    /** �ý���ȯ�溯�� �� */
    private MaConfigDetailDTO maConfigDetailDTO = new MaConfigDetailDTO();
    
	public MaConfigCommonDTO getMaConfigCommonDTO() {
		return maConfigCommonDTO;
	}

	public void setMaConfigCommonDTO(MaConfigCommonDTO maConfigCommonDTO) {
		this.maConfigCommonDTO = maConfigCommonDTO;
	}

	public MaConfigDetailDTO getMaConfigDetailDTO() {
		return maConfigDetailDTO;
	}

	public void setMaConfigDetailDTO(MaConfigDetailDTO maConfigDetailDTO) {
		this.maConfigDetailDTO = maConfigDetailDTO;
	}
}
