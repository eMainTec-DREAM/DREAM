package dream.work.service.form;

import common.struts.BaseForm;
import dream.work.service.dto.WorkServiceCommonDTO;
import dream.work.service.dto.WorkServicePriceDTO;

/**
 * 서비스 설정 목록
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="workServicePriceForm"
 */
public class WorkServicePriceForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private WorkServiceCommonDTO workServiceCommonDTO = new WorkServiceCommonDTO();
    /** 서비스 설정 목록  */
    private WorkServicePriceDTO workServicePriceDTO = new WorkServicePriceDTO();

	public WorkServiceCommonDTO getWorkServiceCommonDTO() {
		return workServiceCommonDTO;
	}

	public void setWorkServiceCommonDTO(WorkServiceCommonDTO workServiceCommonDTO) {
		this.workServiceCommonDTO = workServiceCommonDTO;
	}

	public WorkServicePriceDTO getWorkServicePriceDTO() {
		return workServicePriceDTO;
	}

	public void setWorkServicePriceDTO(WorkServicePriceDTO workServicePriceDTO) {
		this.workServicePriceDTO = workServicePriceDTO;
	}
}
