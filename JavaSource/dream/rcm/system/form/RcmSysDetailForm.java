package dream.rcm.system.form;

import java.util.Collection;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.rcm.system.dto.RcmSysCommonDTO;
import dream.rcm.system.dto.RcmSysDetailDTO;

/**
 * System분석- 상세 Form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="rcmSysDetailForm"
 */
public class RcmSysDetailForm extends BaseForm
{
    //========================================================================
    /** System분석 공통 */ 
    private RcmSysCommonDTO rcmSysCommonDTO = new RcmSysCommonDTO();
    //========================================================================
    /** System분석 상세 */
    private RcmSysDetailDTO rcmSysDetailDTO = new RcmSysDetailDTO();
    
    /** 첨부문서 DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();

	public MaDocLibCommonDTO getMaDocLibCommonDTO() {
		return maDocLibCommonDTO;
	}

	public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO) {
		this.maDocLibCommonDTO = maDocLibCommonDTO;
	}

	public RcmSysCommonDTO getRcmSysCommonDTO() 
	{
		return rcmSysCommonDTO;
	}

	public void setRcmSysCommonDTO(RcmSysCommonDTO rcmSysCommonDTO) 
	{
		this.rcmSysCommonDTO = rcmSysCommonDTO;
	}

	public RcmSysDetailDTO getRcmSysDetailDTO() 
	{
		return rcmSysDetailDTO;
	}

	public void setRcmSysDetailDTO(RcmSysDetailDTO rcmSysDetailDTO) 
	{
		this.rcmSysDetailDTO = rcmSysDetailDTO;
	}

}
