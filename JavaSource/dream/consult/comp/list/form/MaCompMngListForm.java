package dream.consult.comp.list.form;

import common.struts.BaseForm;
import dream.consult.comp.list.dto.MaCompMngCommonDTO;

/**
 * ȸ�缳�� - ��� form
 * @author  kim21017
 * @version $Id: MaCompMngListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maCompMngListForm"
 */
public class MaCompMngListForm extends BaseForm
{    
    //===============================================================
    /** ȸ�缳�� ���� */
    private MaCompMngCommonDTO maCompMngCommonDTO = new MaCompMngCommonDTO();

	public MaCompMngCommonDTO getMaCompMngCommonDTO() {
		return maCompMngCommonDTO;
	}

	public void setMaCompMngCommonDTO(MaCompMngCommonDTO maCompMngCommonDTO) {
		this.maCompMngCommonDTO = maCompMngCommonDTO;
	}

}
