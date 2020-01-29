package dream.work.plan.service.form;

import common.struts.BaseForm;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.plan.service.dto.WoPlanServiceDTO;

/**
 * 서비스작업 Form 
 * @author  nhkim8548
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="woPlanServiceForm"
 */
public class WoPlanServiceForm extends BaseForm
{    
    //=======================================================================
	/** 공통 */
    private WoPlanCommonDTO woPlanCommonDTO = new WoPlanCommonDTO();

    //=======================================================================
	private WoPlanServiceDTO woPlanServiceDTO = new WoPlanServiceDTO();
	
	public WoPlanCommonDTO getWoPlanCommonDTO() {
		return woPlanCommonDTO;
	}

	public void setWoPlanCommonDTO(WoPlanCommonDTO woPlanCommonDTO) {
		this.woPlanCommonDTO = woPlanCommonDTO;
	}

	public WoPlanServiceDTO getWoPlanServiceDTO() {
		return woPlanServiceDTO;
	}

	public void setWoPlanServiceDTO(WoPlanServiceDTO woPlanServiceDTO) {
		this.woPlanServiceDTO = woPlanServiceDTO;
	}
}
