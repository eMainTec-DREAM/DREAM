package dream.work.list.form;

import common.struts.BaseForm;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.dto.WoPlanWoLetDetailDTO;
import dream.work.list.dto.WoPlanWoLetListDTO;

/**
 * �۾���ȹ��� - �����۾�
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="woPlanWoLetDetailForm"
 */
public class WoPlanWoLetDetailForm extends BaseForm
{    
    //===============================================================
    /** ���� DTO */
    private WoPlanCommonDTO woPlanCommonDTO = new WoPlanCommonDTO();
	/** �۾���ȹ��� - �����۾� ��� DTO  */
    private WoPlanWoLetListDTO woPlanWoLetListDTO = new WoPlanWoLetListDTO();
	/** �۾���ȹ��� - �����۾� �� DTO  */
    private WoPlanWoLetDetailDTO woPlanWoLetDetailDTO = new WoPlanWoLetDetailDTO();
    
	public WoPlanWoLetListDTO getWoPlanWoLetListDTO() {
		return woPlanWoLetListDTO;
	}
	public void setWoPlanWoLetListDTO(WoPlanWoLetListDTO woPlanWoLetListDTO) {
		this.woPlanWoLetListDTO = woPlanWoLetListDTO;
	}
	public WoPlanWoLetDetailDTO getWoPlanWoLetDetailDTO() {
		return woPlanWoLetDetailDTO;
	}
	public void setWoPlanWoLetDetailDTO(WoPlanWoLetDetailDTO woPlanWoLetDetailDTO) {
		this.woPlanWoLetDetailDTO = woPlanWoLetDetailDTO;
	}
	public WoPlanCommonDTO getWoPlanCommonDTO() {
		return woPlanCommonDTO;
	}
	public void setWoPlanCommonDTO(WoPlanCommonDTO woPlanCommonDTO) {
		this.woPlanCommonDTO = woPlanCommonDTO;
	}
}
