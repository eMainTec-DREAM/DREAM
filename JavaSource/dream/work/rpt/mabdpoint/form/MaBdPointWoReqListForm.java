package dream.work.rpt.mabdpoint.form;

import common.struts.BaseForm;
import dream.req.work.dto.ReqWorkCommonDTO;
import dream.req.work.dto.ReqWorkDetailDTO;
import dream.work.rpt.mabdpoint.dto.MaBdPointCommonDTO;
import dream.work.rpt.mabdpoint.dto.MaBdPointDetailDTO;
import dream.work.rpt.mabdpoint.dto.MaBdPointWoReqListDTO;

/**
 * 이상점검조치 - 작업요청 목록 form
 * @author  syyang
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maBdPointWoReqListForm"
 */
public class MaBdPointWoReqListForm extends BaseForm
{
    //========================================================================
    /** 이상점검조치 공통 DTO */ 
    private MaBdPointCommonDTO maBdPointCommonDTO = new MaBdPointCommonDTO();
    //========================================================================
    /** 이상점검조치 상세 */
    private MaBdPointDetailDTO maBdPointDetailDTO = new MaBdPointDetailDTO();
    
    /** 이상점검조치 - 작업요청 목록 DTO */
    private MaBdPointWoReqListDTO maBdPointWoReqListDTO = new MaBdPointWoReqListDTO();
    
    /** 작업요청 공통 */
    private ReqWorkCommonDTO reqWorkCommonDTO = new ReqWorkCommonDTO();
    /** 작업요청 상세 */
    private ReqWorkDetailDTO reqWorkDetailDTO = new ReqWorkDetailDTO();
    
    
	public MaBdPointCommonDTO getMaBdPointCommonDTO() {
		return maBdPointCommonDTO;
	}
	public void setMaBdPointCommonDTO(MaBdPointCommonDTO maBdPointCommonDTO) {
		this.maBdPointCommonDTO = maBdPointCommonDTO;
	}
	public MaBdPointDetailDTO getMaBdPointDetailDTO() {
		return maBdPointDetailDTO;
	}
	public void setMaBdPointDetailDTO(MaBdPointDetailDTO maBdPointDetailDTO) {
		this.maBdPointDetailDTO = maBdPointDetailDTO;
	}
	public MaBdPointWoReqListDTO getMaBdPointWoReqListDTO() {
		return maBdPointWoReqListDTO;
	}
	public void setMaBdPointWoReqListDTO(MaBdPointWoReqListDTO maBdPointWoReqListDTO) {
		this.maBdPointWoReqListDTO = maBdPointWoReqListDTO;
	}
	public ReqWorkCommonDTO getReqWorkCommonDTO() {
		return reqWorkCommonDTO;
	}
	public void setReqWorkCommonDTO(ReqWorkCommonDTO reqWorkCommonDTO) {
		this.reqWorkCommonDTO = reqWorkCommonDTO;
	}
	public ReqWorkDetailDTO getReqWorkDetailDTO() {
		return reqWorkDetailDTO;
	}
	public void setReqWorkDetailDTO(ReqWorkDetailDTO reqWorkDetailDTO) {
		this.reqWorkDetailDTO = reqWorkDetailDTO;
	}
    
}
