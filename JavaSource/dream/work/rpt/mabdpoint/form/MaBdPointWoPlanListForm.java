package dream.work.rpt.mabdpoint.form;

import common.struts.BaseForm;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.dto.WoPlanDetailDTO;
import dream.work.rpt.mabdpoint.dto.MaBdPointCommonDTO;
import dream.work.rpt.mabdpoint.dto.MaBdPointDetailDTO;
import dream.work.rpt.mabdpoint.dto.MaBdPointWoPlanListDTO;

/**
 * 이상점검조치 - 작업계획 목록 form
 * @author  syyang
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maBdPointWoPlanListForm"
 */
public class MaBdPointWoPlanListForm extends BaseForm
{    
    //========================================================================
    /** 이상점검조치 공통 DTO */ 
    private MaBdPointCommonDTO maBdPointCommonDTO = new MaBdPointCommonDTO();
    //========================================================================
    /** 이상점검조치 상세 */
    private MaBdPointDetailDTO maBdPointDetailDTO = new MaBdPointDetailDTO();
    
    /** 이상점검조치 - 작업계획 목록 DTO */
    private MaBdPointWoPlanListDTO maBdPointWoPlanListDTO = new MaBdPointWoPlanListDTO();
   
    /** 작업계획 (WOPLAN) */
    private WoPlanCommonDTO woPlanCommonDTO = new WoPlanCommonDTO();
    /** 작업결과 상세 */
    private WoPlanDetailDTO woPlanDetailDTO = new WoPlanDetailDTO();
    
    
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
	public MaBdPointWoPlanListDTO getMaBdPointWoPlanListDTO() {
		return maBdPointWoPlanListDTO;
	}
	public void setMaBdPointWoPlanListDTO(MaBdPointWoPlanListDTO maBdPointWoPlanListDTO) {
		this.maBdPointWoPlanListDTO = maBdPointWoPlanListDTO;
	}
	public WoPlanCommonDTO getWoPlanCommonDTO() {
		return woPlanCommonDTO;
	}
	public void setWoPlanCommonDTO(WoPlanCommonDTO woPlanCommonDTO) {
		this.woPlanCommonDTO = woPlanCommonDTO;
	}
	public WoPlanDetailDTO getWoPlanDetailDTO() {
		return woPlanDetailDTO;
	}
	public void setWoPlanDetailDTO(WoPlanDetailDTO woPlanDetailDTO) {
		this.woPlanDetailDTO = woPlanDetailDTO;
	}
}
