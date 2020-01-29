package dream.req.work.form;

import common.struts.BaseForm;
import dream.req.work.dto.MaWoReqCommonDTO;
import dream.req.work.dto.MaWoReqDetailDTO;
import dream.req.work.dto.MaWoReqResDetailDTO;
import dream.req.work.dto.ReqWoPlanRsltListDTO;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.dto.WoPlanDetailDTO;

/**
 * 작업계획 - 목록 form
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="reqWoPlanRsltListForm"
 */
public class ReqWoPlanRsltListForm extends BaseForm
{    
    //===============================================================
    /** 작업요청 공통 */
    private MaWoReqCommonDTO maWoReqCommonDTO = new MaWoReqCommonDTO();
    /** 작업요청 상세 */
    private MaWoReqDetailDTO maWoReqDetailDTO = new MaWoReqDetailDTO();
    
    /** 작업계획 */
    private ReqWoPlanRsltListDTO reqWoPlanRsltListDTO = new ReqWoPlanRsltListDTO();
    
    private MaWoReqResDetailDTO maWoReqResDetailDTO = new MaWoReqResDetailDTO();
    /** 작업계획 (WOPLAN) */
    private WoPlanCommonDTO woPlanCommonDTO = new WoPlanCommonDTO();
    /** 작업결과 상세 */
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
