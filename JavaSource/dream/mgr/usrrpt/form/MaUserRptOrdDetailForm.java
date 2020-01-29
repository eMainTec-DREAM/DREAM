package dream.mgr.usrrpt.form;

import common.struts.BaseForm;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;
import dream.mgr.usrrpt.dto.MaUserRptOrdDetailDTO;

/**
 * 
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maUserRptOrdDetailForm"
 */
public class MaUserRptOrdDetailForm extends BaseForm
{    
    //===============================================================
    /** 공통 DTO */
    private MaUserRptCommonDTO maUserRptCommonDTO = new MaUserRptCommonDTO();
	/** 사용설비 상세 DTO  */
    private MaUserRptOrdDetailDTO maUserRptOrdDetailDTO = new MaUserRptOrdDetailDTO();
    
	
	public MaUserRptOrdDetailDTO getMaUserRptOrdDetailDTO() 
	{
		return maUserRptOrdDetailDTO;
	}
	
	public void setMaUserRptOrdDetailDTO(MaUserRptOrdDetailDTO maUserRptOrdDetailDTO) 
	{
		this.maUserRptOrdDetailDTO = maUserRptOrdDetailDTO;
	}

	public MaUserRptCommonDTO getMaUserRptCommonDTO() {
		return maUserRptCommonDTO;
	}

	public void setMaUserRptCommonDTO(MaUserRptCommonDTO maUserRptCommonDTO) {
		this.maUserRptCommonDTO = maUserRptCommonDTO;
	}

}
