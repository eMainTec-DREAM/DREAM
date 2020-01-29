package gaia.gapg.form;

import common.struts.BaseForm;
import gaia.gapg.dto.GaPgMngCommonDTO;
import gaia.gapg.dto.GaPgMngDetailDTO;

/**
 * ȭ��- �� Form
 * @author  kim21017
 * @version $Id: GaPgMngDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="gaPgMngDetailForm"
 */
public class GaPgMngDetailForm extends BaseForm
{
    //========================================================================
    /** ȭ�� ���� */ 
    private GaPgMngCommonDTO gaPgMngCommonDTO = new GaPgMngCommonDTO();
    //========================================================================
    /** ȭ�� �� */
    private GaPgMngDetailDTO gaPgMngDetailDTO = new GaPgMngDetailDTO();
    

	public GaPgMngCommonDTO getGaPgMngCommonDTO() {
		return gaPgMngCommonDTO;
	}

    public void setGaPgMngCommonDTO(GaPgMngCommonDTO gaPgMngCommonDTO) {
		this.gaPgMngCommonDTO = gaPgMngCommonDTO;
	}

	public GaPgMngDetailDTO getGaPgMngDetailDTO() {
		return gaPgMngDetailDTO;
	}

	public void setGaPgMngDetailDTO(GaPgMngDetailDTO gaPgMngDetailDTO) {
		this.gaPgMngDetailDTO = gaPgMngDetailDTO;
	}
}
