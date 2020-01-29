package dream.req.work.form;

import common.struts.BaseForm;
import dream.req.work.dto.MaWoReqCommonDTO;
import dream.req.work.dto.MaWoReqDetailDTO;
import dream.req.work.dto.MaWoReqResDetailDTO;
import dream.req.work.dto.ReqWoRsltListDTO;
import dream.work.list.dto.MaWoResultFailDetailDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultMstrDetailDTO;

/**
 * 작업결과 - 목록 form
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="reqWoRsltListForm"
 */
public class ReqWoRsltListForm extends BaseForm
{    
    //===============================================================
    /** 작업요청 공통 */
    private MaWoReqCommonDTO maWoReqCommonDTO = new MaWoReqCommonDTO();
    /** 작업요청 상세 */
    private MaWoReqDetailDTO maWoReqDetailDTO = new MaWoReqDetailDTO();
    
    /** 작업결과 */
    private ReqWoRsltListDTO reqWoRsltListDTO = new ReqWoRsltListDTO();
    private MaWoReqResDetailDTO maWoReqResDetailDTO = new MaWoReqResDetailDTO();
    /** 작업 */
    private MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();
    private MaWoResultMstrDetailDTO maWoResultMstrDetailDTO = new MaWoResultMstrDetailDTO();
    /** 작업결과 고장내역 */
    private MaWoResultFailDetailDTO maWoResultFailDetailDTO = new MaWoResultFailDetailDTO();
    
    
	public MaWoReqDetailDTO getMaWoReqDetailDTO() {
		return maWoReqDetailDTO;
	}

	public void setMaWoReqDetailDTO(MaWoReqDetailDTO maWoReqDetailDTO) {
		this.maWoReqDetailDTO = maWoReqDetailDTO;
	}

	public MaWoResultMstrDetailDTO getMaWoResultMstrDetailDTO() {
		return maWoResultMstrDetailDTO;
	}

	public void setMaWoResultMstrDetailDTO(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO) {
		this.maWoResultMstrDetailDTO = maWoResultMstrDetailDTO;
	}

	public MaWoReqResDetailDTO getMaWoReqResDetailDTO() {
		return maWoReqResDetailDTO;
	}

	public void setMaWoReqResDetailDTO(MaWoReqResDetailDTO maWoReqResDetailDTO) {
		this.maWoReqResDetailDTO = maWoReqResDetailDTO;
	}

	public MaWoResultMstrCommonDTO getMaWoResultMstrCommonDTO() {
		return maWoResultMstrCommonDTO;
	}

	public void setMaWoResultMstrCommonDTO(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) {
		this.maWoResultMstrCommonDTO = maWoResultMstrCommonDTO;
	}

	public MaWoReqCommonDTO getMaWoReqCommonDTO() {
		return maWoReqCommonDTO;
	}

	public void setMaWoReqCommonDTO(MaWoReqCommonDTO maWoReqCommonDTO) {
		this.maWoReqCommonDTO = maWoReqCommonDTO;
	}

	public ReqWoRsltListDTO getReqWoRsltListDTO() {
		return reqWoRsltListDTO;
	}

	public void setReqWoRsltListDTO(ReqWoRsltListDTO reqWoRsltListDTO) {
		this.reqWoRsltListDTO = reqWoRsltListDTO;
	}

	public MaWoResultFailDetailDTO getMaWoResultFailDetailDTO() {
		return maWoResultFailDetailDTO;
	}

	public void setMaWoResultFailDetailDTO(MaWoResultFailDetailDTO maWoResultFailDetailDTO) {
		this.maWoResultFailDetailDTO = maWoResultFailDetailDTO;
	}
	
	
}
