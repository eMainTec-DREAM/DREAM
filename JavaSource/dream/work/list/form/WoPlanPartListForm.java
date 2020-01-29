package dream.work.list.form;

import common.struts.BaseForm;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.dto.WoPlanDetailDTO;
import dream.work.list.dto.WoPlanPartDetailDTO;
import dream.work.list.dto.WoPlanPartListDTO;

/**
 * 작업계획목록 - 투입부품 목록
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="woPlanPartListForm"
 */
public class WoPlanPartListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private WoPlanCommonDTO woPlanCommonDTO = new WoPlanCommonDTO();
    /** 상세 */
    private WoPlanDetailDTO woPlanDetailDTO = new WoPlanDetailDTO();
    /** 작업계획목록 - 투입부품 목록  */
    private WoPlanPartListDTO woPlanPartListDTO = new WoPlanPartListDTO();
    /** 작업계획목록 - 투입부품 상세  */
    private WoPlanPartDetailDTO woPlanPartDetailDTO = new WoPlanPartDetailDTO();
	
	public WoPlanDetailDTO getWoPlanDetailDTO()
    {
        return woPlanDetailDTO;
    }

    public void setWoPlanDetailDTO(
            WoPlanDetailDTO woPlanDetailDTO)
    {
        this.woPlanDetailDTO = woPlanDetailDTO;
    }

    public WoPlanPartDetailDTO getWoPlanPartDetailDTO()
    {
        return woPlanPartDetailDTO;
    }

    public void setWoPlanPartDetailDTO(
            WoPlanPartDetailDTO woPlanPartDetailDTO)
    {
        this.woPlanPartDetailDTO = woPlanPartDetailDTO;
    }

    public WoPlanCommonDTO getWoPlanCommonDTO() {
		return woPlanCommonDTO;
	}

	public void setWoPlanCommonDTO(WoPlanCommonDTO woPlanCommonDTO) {
		this.woPlanCommonDTO = woPlanCommonDTO;
	}

	public WoPlanPartListDTO getWoPlanPartListDTO() {
		return woPlanPartListDTO;
	}

	public void setWoPlanPartListDTO(WoPlanPartListDTO woPlanPartListDTO) {
		this.woPlanPartListDTO = woPlanPartListDTO;
	}
}
