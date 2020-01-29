package dream.work.service.form;

import common.struts.BaseForm;
import dream.work.service.dto.WorkServiceCommonDTO;
import dream.work.service.dto.WorkServicePriceDTO;

/**
 * ���� ���� ���
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="workServicePriceForm"
 */
public class WorkServicePriceForm extends BaseForm
{    
    //===============================================================
    /** ���� */
    private WorkServiceCommonDTO workServiceCommonDTO = new WorkServiceCommonDTO();
    /** ���� ���� ���  */
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
