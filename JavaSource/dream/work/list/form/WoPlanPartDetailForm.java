package dream.work.list.form;

import common.struts.BaseForm;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.dto.WoPlanDetailDTO;
import dream.work.list.dto.WoPlanPartDetailDTO;
import dream.work.list.dto.WoPlanPartListDTO;

/**
 * 작업계획목록 - 투입부품 상세
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="woPlanPartDetailForm"
 */
public class WoPlanPartDetailForm extends BaseForm
{    
    //===============================================================
    /** 공통 DTO */
    private WoPlanCommonDTO woPlanCommonDTO = new WoPlanCommonDTO();
    /** 상세 */
    private WoPlanDetailDTO woPlanDetailDTO = new WoPlanDetailDTO();
    
	/** 작업계획목록 - 투입부품 목록 DTO  */
    private WoPlanPartListDTO woPlanPartListDTO = new WoPlanPartListDTO();
	/** 작업계획목록 - 투입부품 상세 DTO  */
    private WoPlanPartDetailDTO woPlanPartDetailDTO = new WoPlanPartDetailDTO();
    
	public WoPlanPartListDTO getWoPlanPartListDTO() {
		return woPlanPartListDTO;
	}
	public WoPlanDetailDTO getWoPlanDetailDTO()
    {
        return woPlanDetailDTO;
    }
    public void setWoPlanDetailDTO(
            WoPlanDetailDTO woPlanDetailDTO)
    {
        this.woPlanDetailDTO = woPlanDetailDTO;
    }
    public void setWoPlanPartListDTO(WoPlanPartListDTO woPlanPartListDTO) {
		this.woPlanPartListDTO = woPlanPartListDTO;
	}
	public WoPlanPartDetailDTO getWoPlanPartDetailDTO() {
		return woPlanPartDetailDTO;
	}
	public void setWoPlanPartDetailDTO(WoPlanPartDetailDTO woPlanPartDetailDTO) {
		this.woPlanPartDetailDTO = woPlanPartDetailDTO;
	}
	public WoPlanCommonDTO getWoPlanCommonDTO() {
		return woPlanCommonDTO;
	}
	public void setWoPlanCommonDTO(WoPlanCommonDTO woPlanCommonDTO) {
		this.woPlanCommonDTO = woPlanCommonDTO;
	}
}
