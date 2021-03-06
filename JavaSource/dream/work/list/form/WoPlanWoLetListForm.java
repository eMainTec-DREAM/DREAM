package dream.work.list.form;

import common.struts.BaseForm;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.dto.WoPlanWoLetDetailDTO;
import dream.work.list.dto.WoPlanWoLetListDTO;

/**
 * 작업계획목록 - 안전작업 목록
 * @author  syyang
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="woPlanWoLetListForm"
 */
public class WoPlanWoLetListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private WoPlanCommonDTO woPlanCommonDTO = new WoPlanCommonDTO();
    /** 작업계획목록 - 안전작업 목록  */
    private WoPlanWoLetListDTO woPlanWoLetListDTO = new WoPlanWoLetListDTO();
    /** 작업계획목록 - 안전작업 상세 DTO  */
    private WoPlanWoLetDetailDTO woPlanWoLetDetailDTO = new WoPlanWoLetDetailDTO();

	public WoPlanWoLetDetailDTO getWoPlanWoLetDetailDTO()
    {
        return woPlanWoLetDetailDTO;
    }

    public void setWoPlanWoLetDetailDTO(WoPlanWoLetDetailDTO woPlanWoLetDetailDTO)
    {
        this.woPlanWoLetDetailDTO = woPlanWoLetDetailDTO;
    }

    public WoPlanCommonDTO getWoPlanCommonDTO() {
		return woPlanCommonDTO;
	}

	public void setWoPlanCommonDTO(WoPlanCommonDTO woPlanCommonDTO) {
		this.woPlanCommonDTO = woPlanCommonDTO;
	}

	public WoPlanWoLetListDTO getWoPlanWoLetListDTO() {
		return woPlanWoLetListDTO;
	}

	public void setWoPlanWoLetListDTO(WoPlanWoLetListDTO woPlanWoLetListDTO) {
		this.woPlanWoLetListDTO = woPlanWoLetListDTO;
	}
}
