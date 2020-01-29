package dream.mgr.manst.form;

import common.struts.BaseForm;
import dream.mgr.manst.dto.MaNstGrpCommonDTO;
import dream.mgr.manst.dto.MaNstGrpDetailDTO;

/**
 * ��������ǥ����- �� Form
 * @author  kim21017
 * @version $Id: MaNstGrpDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maNstGrpDetailForm"
 */
public class MaNstGrpDetailForm extends BaseForm
{
    //========================================================================
    /** ���� */ 
    private MaNstGrpCommonDTO maNstGrpCommonDTO = new MaNstGrpCommonDTO();
    //========================================================================
    /** �� */
    private MaNstGrpDetailDTO maNstGrpDetailDTO = new MaNstGrpDetailDTO();
    

	public MaNstGrpCommonDTO getMaNstGrpCommonDTO() {
		return maNstGrpCommonDTO;
	}

	public void setMaNstGrpCommonDTO(MaNstGrpCommonDTO maNstGrpCommonDTO) {
		this.maNstGrpCommonDTO = maNstGrpCommonDTO;
	}

	public MaNstGrpDetailDTO getMaNstGrpDetailDTO() {
		return maNstGrpDetailDTO;
	}

	public void setMaNstGrpDetailDTO(MaNstGrpDetailDTO maNstGrpDetailDTO) {
		this.maNstGrpDetailDTO = maNstGrpDetailDTO;
	}

}
