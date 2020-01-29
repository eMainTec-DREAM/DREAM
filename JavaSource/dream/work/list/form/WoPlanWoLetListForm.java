package dream.work.list.form;

import common.struts.BaseForm;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.dto.WoPlanWoLetDetailDTO;
import dream.work.list.dto.WoPlanWoLetListDTO;

/**
 * �۾���ȹ��� - �����۾� ���
 * @author  syyang
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="woPlanWoLetListForm"
 */
public class WoPlanWoLetListForm extends BaseForm
{    
    //===============================================================
    /** ���� */
    private WoPlanCommonDTO woPlanCommonDTO = new WoPlanCommonDTO();
    /** �۾���ȹ��� - �����۾� ���  */
    private WoPlanWoLetListDTO woPlanWoLetListDTO = new WoPlanWoLetListDTO();
    /** �۾���ȹ��� - �����۾� �� DTO  */
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
