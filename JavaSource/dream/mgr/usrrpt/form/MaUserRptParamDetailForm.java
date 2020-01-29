package dream.mgr.usrrpt.form;

import common.struts.BaseForm;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;
import dream.mgr.usrrpt.dto.MaUserRptParamDetailDTO;

/**
 * 
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maUserRptParamDetailForm"
 */
public class MaUserRptParamDetailForm extends BaseForm
{    
    //===============================================================
    /** 공통 DTO */
    private MaUserRptCommonDTO maUserRptCommonDTO = new MaUserRptCommonDTO();
	/** 사용설비 상세 DTO  */
    private MaUserRptParamDetailDTO maUserRptParamDetailDTO = new MaUserRptParamDetailDTO();
    
	
	public MaUserRptParamDetailDTO getMaUserRptParamDetailDTO() 
	{
		return maUserRptParamDetailDTO;
	}
	
	public void setMaUserRptParamDetailDTO(MaUserRptParamDetailDTO maUserRptParamDetailDTO) 
	{
		this.maUserRptParamDetailDTO = maUserRptParamDetailDTO;
	}

	public MaUserRptCommonDTO getMaUserRptCommonDTO() {
		return maUserRptCommonDTO;
	}

	public void setMaUserRptCommonDTO(MaUserRptCommonDTO maUserRptCommonDTO) {
		this.maUserRptCommonDTO = maUserRptCommonDTO;
	}

}
