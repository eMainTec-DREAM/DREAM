package dream.req.work.form;

import common.struts.BaseForm;
import dream.invt.list.dto.InvtCommonDTO;
import dream.invt.list.dto.InvtDetailDTO;
import dream.req.work.dto.MaWoReqCommonDTO;
import dream.req.work.dto.ReqWoInvtRsltListDTO;

/**
 * 투자결과 - 목록 form
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="reqWoInvtRsltListForm"
 */
public class ReqWoInvtRsltListForm extends BaseForm
{    
    //===============================================================
    /** 작업요청 공통 */
    private MaWoReqCommonDTO maWoReqCommonDTO = new MaWoReqCommonDTO();
    /** 투자결과 */
    private ReqWoInvtRsltListDTO reqWoInvtRsltListDTO = new ReqWoInvtRsltListDTO();
    /** 투자 */
    private InvtCommonDTO invtCommonDTO = new InvtCommonDTO();
    /** 투자상세 */
    private InvtDetailDTO invtDetailDTO = new InvtDetailDTO();
    
	public InvtDetailDTO getInvtDetailDTO()
    {
        return invtDetailDTO;
    }

    public void setInvtDetailDTO(InvtDetailDTO invtDetailDTO)
    {
        this.invtDetailDTO = invtDetailDTO;
    }

    public InvtCommonDTO getInvtCommonDTO() {
		return invtCommonDTO;
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

	public ReqWoInvtRsltListDTO getReqWoInvtRsltListDTO() {
		return reqWoInvtRsltListDTO;
	}

	public void setReqWoInvtRsltListDTO(ReqWoInvtRsltListDTO reqWoInvtRsltListDTO) {
		this.reqWoInvtRsltListDTO = reqWoInvtRsltListDTO;
	}
	
}
