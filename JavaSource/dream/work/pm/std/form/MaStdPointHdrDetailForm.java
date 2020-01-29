package dream.work.pm.std.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.work.pm.std.dto.MaStdPointCommonDTO;
import dream.work.pm.std.dto.MaStdPointHdrDetailDTO;

/**
 * ǥ���׸�- �� Form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="maStdPointHdrDetailForm"
 */
public class MaStdPointHdrDetailForm extends BaseForm
{
    //========================================================================
    /** ǥ���׸� ���� */ 
    private MaStdPointCommonDTO maStdPointCommonDTO = new MaStdPointCommonDTO();
    //========================================================================
    /** ǥ���׸� �� */
    private MaStdPointHdrDetailDTO maStdPointHdrDetailDTO = new MaStdPointHdrDetailDTO();
    /** ÷�ι��� DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();
    
	public MaDocLibCommonDTO getMaDocLibCommonDTO() {
		return maDocLibCommonDTO;
	}
	public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO) {
		this.maDocLibCommonDTO = maDocLibCommonDTO;
	}
	public MaStdPointCommonDTO getMaStdPointCommonDTO() {
		return maStdPointCommonDTO;
	}
	public void setMaStdPointCommonDTO(MaStdPointCommonDTO maStdPointCommonDTO) {
		this.maStdPointCommonDTO = maStdPointCommonDTO;
	}
	public MaStdPointHdrDetailDTO getMaStdPointHdrDetailDTO() {
		return maStdPointHdrDetailDTO;
	}
	public void setMaStdPointHdrDetailDTO(MaStdPointHdrDetailDTO maStdPointHdrDetailDTO) {
		this.maStdPointHdrDetailDTO = maStdPointHdrDetailDTO;
	}
}
