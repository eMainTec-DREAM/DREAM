package dream.req.work.form;

import common.struts.BaseForm;
import dream.req.work.dto.MaWoReqCommonDTO;
import dream.req.work.dto.MaWoReqDetailDTO;
import dream.req.work.dto.MaWoReqResDetailDTO;
import dream.req.work.dto.ReqWoPlanRsltListDTO;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.dto.WoPlanDetailDTO;

/**
 * �۾���ȹ - ��� form
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="reqWoPlanRsltListForm"
 */
public class ReqWoPlanRsltListForm extends BaseForm
{    
    //===============================================================
    /** �۾���û ���� */
    private MaWoReqCommonDTO maWoReqCommonDTO = new MaWoReqCommonDTO();
    /** �۾���û �� */
    private MaWoReqDetailDTO maWoReqDetailDTO = new MaWoReqDetailDTO();
    
    /** �۾���ȹ */
    private ReqWoPlanRsltListDTO reqWoPlanRsltListDTO = new ReqWoPlanRsltListDTO();
    
    private MaWoReqResDetailDTO maWoReqResDetailDTO = new MaWoReqResDetailDTO();
    /** �۾���ȹ (WOPLAN) */
    private WoPlanCommonDTO woPlanCommonDTO = new WoPlanCommonDTO();
    /** �۾���� �� */
    private WoPlanDetailDTO woPlanDetailDTO = new WoPlanDetailDTO();
    
    
	public WoPlanDetailDTO getWoPlanDetailDTO() {
		return woPlanDetailDTO;
	}

	public void setWoPlanDetailDTO(WoPlanDetailDTO woPlanDetailDTO) {
		this.woPlanDetailDTO = woPlanDetailDTO;
	}

	public MaWoReqDetailDTO getMaWoReqDetailDTO() {
		return maWoReqDetailDTO;
	}

	public void setMaWoReqDetailDTO(MaWoReqDetailDTO maWoReqDetailDTO) {
		this.maWoReqDetailDTO = maWoReqDetailDTO;
	}

	public MaWoReqResDetailDTO getMaWoReqResDetailDTO() {
		return maWoReqResDetailDTO;
	}

	public WoPlanCommonDTO getWoPlanCommonDTO() {
		return woPlanCommonDTO;
	}

	public void setWoPlanCommonDTO(WoPlanCommonDTO woPlanCommonDTO) {
		this.woPlanCommonDTO = woPlanCommonDTO;
	}

	public void setMaWoReqResDetailDTO(MaWoReqResDetailDTO maWoReqResDetailDTO) {
		this.maWoReqResDetailDTO = maWoReqResDetailDTO;
	}

	public MaWoReqCommonDTO getMaWoReqCommonDTO() {
		return maWoReqCommonDTO;
	}

	public void setMaWoReqCommonDTO(MaWoReqCommonDTO maWoReqCommonDTO) {
		this.maWoReqCommonDTO = maWoReqCommonDTO;
	}

	public ReqWoPlanRsltListDTO getReqWoPlanRsltListDTO() {
		return reqWoPlanRsltListDTO;
	}

	public void setReqWoPlanRsltListDTO(ReqWoPlanRsltListDTO reqWoPlanRsltListDTO) {
		this.reqWoPlanRsltListDTO = reqWoPlanRsltListDTO;
	}
	
}
