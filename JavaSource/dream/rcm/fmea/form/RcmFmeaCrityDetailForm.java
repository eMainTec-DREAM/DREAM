package dream.rcm.fmea.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.rcm.fmea.dto.RcmFmeaCommonDTO;
import dream.rcm.fmea.dto.RcmFmeaCrityDetailDTO;
import dream.rcm.fmea.dto.RcmFmeaCrityListDTO;

/**
 * 
 * @author  kim2107
 * @version $Id: RcmFmeaCrityDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="rcmFmeaCrityDetailForm"
 */
public class RcmFmeaCrityDetailForm extends BaseForm
{    
    //===============================================================
    /** 공통 DTO */
    private RcmFmeaCommonDTO rcmFmeaCommonDTO = new RcmFmeaCommonDTO();
	/** 답변  DTO  */
    private RcmFmeaCrityListDTO rcmFmeaCrityListDTO = new RcmFmeaCrityListDTO();
	/** 답변  Detail DTO  */
    private RcmFmeaCrityDetailDTO rcmFmeaCrityDetailDTO = new RcmFmeaCrityDetailDTO();
    /** 첨부문서 DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();
    
	public MaDocLibCommonDTO getMaDocLibCommonDTO() {
		return maDocLibCommonDTO;
	}
	public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO) {
		this.maDocLibCommonDTO = maDocLibCommonDTO;
	}
	public RcmFmeaCrityListDTO getRcmFmeaCrityListDTO() {
		return rcmFmeaCrityListDTO;
	}
	public void setRcmFmeaCrityListDTO(RcmFmeaCrityListDTO rcmFmeaCrityListDTO) {
		this.rcmFmeaCrityListDTO = rcmFmeaCrityListDTO;
	}
	public RcmFmeaCrityDetailDTO getRcmFmeaCrityDetailDTO() {
		return rcmFmeaCrityDetailDTO;
	}
	public void setRcmFmeaCrityDetailDTO(RcmFmeaCrityDetailDTO rcmFmeaCrityDetailDTO) {
		this.rcmFmeaCrityDetailDTO = rcmFmeaCrityDetailDTO;
	}
	public RcmFmeaCommonDTO getRcmFmeaCommonDTO() {
		return rcmFmeaCommonDTO;
	}
	public void setRcmFmeaCommonDTO(RcmFmeaCommonDTO rcmFmeaCommonDTO) {
		this.rcmFmeaCommonDTO = rcmFmeaCommonDTO;
	}
	
}
