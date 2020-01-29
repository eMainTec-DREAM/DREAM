package dream.consult.comp.list.form;

import common.struts.BaseForm;
import dream.consult.comp.list.dto.MaCompMngCommonDTO;
import dream.consult.comp.list.dto.MaCompMngDetailDTO;
import dream.doc.img.dto.MaDocImgCommonDTO;

/**
 * 회사설정- 상세 Form
 * @author  kim21017
 * @version $Id: MaCompMngDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maCompMngDetailForm"
 */
public class MaCompMngDetailForm extends BaseForm
{
    //========================================================================
    /** 회사설정 공통 */ 
    private MaCompMngCommonDTO maCompMngCommonDTO = new MaCompMngCommonDTO();
    //========================================================================
    /** 회사설정 상세 */
    private MaCompMngDetailDTO maCompMngDetailDTO = new MaCompMngDetailDTO();
    private MaDocImgCommonDTO maDocImgCommonDTO = new MaDocImgCommonDTO();

	public MaDocImgCommonDTO getMaDocImgCommonDTO() {
		return maDocImgCommonDTO;
	}

	public void setMaDocImgCommonDTO(MaDocImgCommonDTO maDocImgCommonDTO) {
		this.maDocImgCommonDTO = maDocImgCommonDTO;
	}

	public MaCompMngCommonDTO getMaCompMngCommonDTO() {
		return maCompMngCommonDTO;
	}

	public void setMaCompMngCommonDTO(MaCompMngCommonDTO maCompMngCommonDTO) {
		this.maCompMngCommonDTO = maCompMngCommonDTO;
	}

	public MaCompMngDetailDTO getMaCompMngDetailDTO() {
		return maCompMngDetailDTO;
	}

	public void setMaCompMngDetailDTO(MaCompMngDetailDTO maCompMngDetailDTO) {
		this.maCompMngDetailDTO = maCompMngDetailDTO;
	}

}
