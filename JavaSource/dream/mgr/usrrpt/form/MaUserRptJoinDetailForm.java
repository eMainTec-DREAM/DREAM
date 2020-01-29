package dream.mgr.usrrpt.form;

import common.struts.BaseForm;
import dream.mgr.usrrpt.dto.MaUserRptCommonDTO;
import dream.mgr.usrrpt.dto.MaUserRptJoinDetailDTO;

/**
 * 
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maUserRptJoinDetailForm"
 */
public class MaUserRptJoinDetailForm extends BaseForm
{    
    //===============================================================
    /** 공통 DTO */
    private MaUserRptCommonDTO maUserRptCommonDTO = new MaUserRptCommonDTO();
	/** 사용설비 상세 DTO  */
    private MaUserRptJoinDetailDTO maUserRptJoinDetailDTO = new MaUserRptJoinDetailDTO();
	
	public MaUserRptJoinDetailDTO getMaUserRptJoinDetailDTO() 
	{
		return maUserRptJoinDetailDTO;
	}
	
	public void setMaUserRptJoinDetailDTO(MaUserRptJoinDetailDTO maUserRptJoinDetailDTO) 
	{
		this.maUserRptJoinDetailDTO = maUserRptJoinDetailDTO;
	}

	public MaUserRptCommonDTO getMaUserRptCommonDTO() {
		return maUserRptCommonDTO;
	}

	public void setMaUserRptCommonDTO(MaUserRptCommonDTO maUserRptCommonDTO) {
		this.maUserRptCommonDTO = maUserRptCommonDTO;
	}

}
