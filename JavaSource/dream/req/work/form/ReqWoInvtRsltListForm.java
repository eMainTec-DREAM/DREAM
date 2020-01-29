package dream.req.work.form;

import common.struts.BaseForm;
import dream.invt.list.dto.InvtCommonDTO;
import dream.invt.list.dto.InvtDetailDTO;
import dream.req.work.dto.MaWoReqCommonDTO;
import dream.req.work.dto.ReqWoInvtRsltListDTO;

/**
 * ���ڰ�� - ��� form
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="reqWoInvtRsltListForm"
 */
public class ReqWoInvtRsltListForm extends BaseForm
{    
    //===============================================================
    /** �۾���û ���� */
    private MaWoReqCommonDTO maWoReqCommonDTO = new MaWoReqCommonDTO();
    /** ���ڰ�� */
    private ReqWoInvtRsltListDTO reqWoInvtRsltListDTO = new ReqWoInvtRsltListDTO();
    /** ���� */
    private InvtCommonDTO invtCommonDTO = new InvtCommonDTO();
    /** ���ڻ� */
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
