package dream.pers.priv.info.form;

import common.struts.BaseForm;
import dream.pers.priv.info.dto.MaChangePwDTO;

/**
 * ��й�ȣ����
 * @author  kim2107
 * @version $Id: MaChangePwForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="maChangePwForm"
 */
public class MaChangePwForm extends BaseForm
{    
    //===============================================================
    /** ��й�ȣ���� DTO  */
    private MaChangePwDTO maChangePwDTO = new MaChangePwDTO();

    public MaChangePwDTO getMaChangePwDTO() {
		return maChangePwDTO;
	}
	public void setMaChangePwDTO(MaChangePwDTO maChangePwDTO) {
		this.maChangePwDTO = maChangePwDTO;
	}
}
