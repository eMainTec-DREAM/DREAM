package dream.mgr.usrrpt.form;

import common.struts.BaseForm;
import dream.mgr.usrrpt.dto.MaUserRptColDetailDTO;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;

/**
 * 
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maUserRptColDetailForm"
 */
public class MaUserRptColDetailForm extends BaseForm
{    
    //===============================================================
    /** 공통 DTO */
    private MaUserRptCommonDTO maUserRptCommonDTO = new MaUserRptCommonDTO();
	/** 사용설비 상세 DTO  */
    private MaUserRptColDetailDTO maUserRptColDetailDTO = new MaUserRptColDetailDTO();
    
	
	public MaUserRptColDetailDTO getMaUserRptColDetailDTO() 
	{
		return maUserRptColDetailDTO;
	}
	
	public void setMaUserRptColDetailDTO(MaUserRptColDetailDTO maUserRptColDetailDTO) 
	{
		this.maUserRptColDetailDTO = maUserRptColDetailDTO;
	}

	public MaUserRptCommonDTO getMaUserRptCommonDTO() {
		return maUserRptCommonDTO;
	}

	public void setMaUserRptCommonDTO(MaUserRptCommonDTO maUserRptCommonDTO) {
		this.maUserRptCommonDTO = maUserRptCommonDTO;
	}

}
