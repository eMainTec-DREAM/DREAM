package dream.work.list.form;

import common.struts.BaseForm;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.dto.WoPlanCraftDetailDTO;
import dream.work.list.dto.WoPlanCraftListDTO;

/**
 * 작업계획목록 - 작업자 목록
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="woPlanCraftListForm"
 */
public class WoPlanCraftListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private WoPlanCommonDTO woPlanCommonDTO = new WoPlanCommonDTO();
    /** 작업계획목록 - 작업자 목록  */
    private WoPlanCraftListDTO woPlanCraftListDTO = new WoPlanCraftListDTO();
    /** 작업계획목록 - 작업자 상세 DTO  */
    private WoPlanCraftDetailDTO woPlanCraftDetailDTO = new WoPlanCraftDetailDTO();

	public WoPlanCraftDetailDTO getWoPlanCraftDetailDTO()
    {
        return woPlanCraftDetailDTO;
    }

    public void setWoPlanCraftDetailDTO(WoPlanCraftDetailDTO woPlanCraftDetailDTO)
    {
        this.woPlanCraftDetailDTO = woPlanCraftDetailDTO;
    }

    public WoPlanCommonDTO getWoPlanCommonDTO() {
		return woPlanCommonDTO;
	}

	public void setWoPlanCommonDTO(WoPlanCommonDTO woPlanCommonDTO) {
		this.woPlanCommonDTO = woPlanCommonDTO;
	}

	public WoPlanCraftListDTO getWoPlanCraftListDTO() {
		return woPlanCraftListDTO;
	}

	public void setWoPlanCraftListDTO(WoPlanCraftListDTO woPlanCraftListDTO) {
		this.woPlanCraftListDTO = woPlanCraftListDTO;
	}
}
