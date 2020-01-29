package dream.req.work.form;

import common.struts.BaseForm;
import dream.invt.list.dto.InvtCommonDTO;
import dream.req.work.dto.MaWoReqCommonDTO;
import dream.req.work.dto.MaWoReqResDetailDTO;
import dream.req.work.dto.MaWoReqResListDTO;
import dream.req.work.dto.ReqWoInvtRsltListDTO;

/**
 * 처리사항 - 목록 form
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maWoReqResListForm"
 */
public class MaWoReqResListForm extends BaseForm
{    
    //===============================================================
    /** 작업요청 공통 */
    private MaWoReqCommonDTO maWoReqCommonDTO = new MaWoReqCommonDTO();
    /** 처리사항 */
    private MaWoReqResListDTO maWoReqResListDTO = new MaWoReqResListDTO();
    /** 투자 */
    private InvtCommonDTO invtCommonDTO = new InvtCommonDTO();
    
    /** 작업결과 */
    private MaWoReqResDetailDTO maWoReqResDetailDTO = new MaWoReqResDetailDTO();
    
    /** 투자결과 */
    private ReqWoInvtRsltListDTO reqWoInvtRsltListDTO = new ReqWoInvtRsltListDTO();
    
    public MaWoReqResDetailDTO getMaWoReqResDetailDTO()
    {
        return maWoReqResDetailDTO;
    }

    public void setMaWoReqResDetailDTO(MaWoReqResDetailDTO maWoReqResDetailDTO)
    {
        this.maWoReqResDetailDTO = maWoReqResDetailDTO;
    }

    public InvtCommonDTO getInvtCommonDTO() {
		return invtCommonDTO;
	}

	public ReqWoInvtRsltListDTO getReqWoInvtRsltListDTO() {
		return reqWoInvtRsltListDTO;
	}

	public void setReqWoInvtRsltListDTO(ReqWoInvtRsltListDTO reqWoInvtRsltListDTO) {
		this.reqWoInvtRsltListDTO = reqWoInvtRsltListDTO;
	}

	public void setInvtCommonDTO(InvtCommonDTO invtCommonDTO) {
		this.invtCommonDTO = invtCommonDTO;
	}

	public MaWoReqCommonDTO getMaWoReqCommonDTO() {
		return maWoReqCommonDTO;
	}

	public void setMaWoReqCommonDTO(MaWoReqCommonDTO maWoReqCommonDTO) {
		this.maWoReqCommonDTO = maWoReqCommonDTO;
	}

	public MaWoReqResListDTO getMaWoReqResListDTO() {
		return maWoReqResListDTO;
	}

	public void setMaWoReqResListDTO(MaWoReqResListDTO maWoReqResListDTO) {
		this.maWoReqResListDTO = maWoReqResListDTO;
	}
	
}
