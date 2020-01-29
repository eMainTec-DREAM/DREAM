package dream.work.rpt.mabdpoint.form;

import common.struts.BaseForm;
import dream.req.work.dto.ReqWorkCommonDTO;
import dream.req.work.dto.ReqWorkDetailDTO;
import dream.work.rpt.mabdpoint.dto.MaBdPointCommonDTO;
import dream.work.rpt.mabdpoint.dto.MaBdPointDetailDTO;
import dream.work.rpt.mabdpoint.dto.MaBdPointWoReqListDTO;

/**
 * �̻�������ġ - �۾���û ��� form
 * @author  syyang
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maBdPointWoReqListForm"
 */
public class MaBdPointWoReqListForm extends BaseForm
{
    //========================================================================
    /** �̻�������ġ ���� DTO */ 
    private MaBdPointCommonDTO maBdPointCommonDTO = new MaBdPointCommonDTO();
    //========================================================================
    /** �̻�������ġ �� */
    private MaBdPointDetailDTO maBdPointDetailDTO = new MaBdPointDetailDTO();
    
    /** �̻�������ġ - �۾���û ��� DTO */
    private MaBdPointWoReqListDTO maBdPointWoReqListDTO = new MaBdPointWoReqListDTO();
    
    /** �۾���û ���� */
    private ReqWorkCommonDTO reqWorkCommonDTO = new ReqWorkCommonDTO();
    /** �۾���û �� */
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
