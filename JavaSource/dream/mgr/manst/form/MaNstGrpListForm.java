package dream.mgr.manst.form;

import common.struts.BaseForm;
import dream.mgr.manst.dto.MaNstGrpCommonDTO;

/**
 * 무정지대표라인 - 목록
 * @author  kim21017
 * @version $Id: MaNstGrpListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maNstGrpListForm"
 */
public class MaNstGrpListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private MaNstGrpCommonDTO maNstGrpCommonDTO = new MaNstGrpCommonDTO();

	public MaNstGrpCommonDTO getMaNstGrpCommonDTO() {
		return maNstGrpCommonDTO;
	}

	public void setMaNstGrpCommonDTO(MaNstGrpCommonDTO maNstGrpCommonDTO) {
		this.maNstGrpCommonDTO = maNstGrpCommonDTO;
	}

}
