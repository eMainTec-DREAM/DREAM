package dream.rcm.taskmap.form;

import common.struts.BaseForm;
import dream.rcm.taskmap.dto.RcmTaskMapItemListDTO;
import dream.rcm.taskmap.dto.RcmTaskMapCommonDTO;

/**
 * �亯- ���
 * @author  kim21017
 * @version $Id: RcmTaskMapItemListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="rcmTaskMapItemListForm"
 */
public class RcmTaskMapItemListForm extends BaseForm
{    
    //===============================================================
    /** ���� ���� */
    private RcmTaskMapCommonDTO rcmTaskMapCommonDTO = new RcmTaskMapCommonDTO();
    /** �亯  */
    private RcmTaskMapItemListDTO rcmTaskMapItemListDTO = new RcmTaskMapItemListDTO();
    
	public RcmTaskMapCommonDTO getRcmTaskMapCommonDTO() {
		return rcmTaskMapCommonDTO;
	}

	public void setRcmTaskMapCommonDTO(RcmTaskMapCommonDTO rcmTaskMapCommonDTO) {
		this.rcmTaskMapCommonDTO = rcmTaskMapCommonDTO;
	}

	public RcmTaskMapItemListDTO getRcmTaskMapItemListDTO() {
		return rcmTaskMapItemListDTO;
	}

	public void setRcmTaskMapItemListDTO(RcmTaskMapItemListDTO rcmTaskMapItemListDTO) {
		this.rcmTaskMapItemListDTO = rcmTaskMapItemListDTO;
	}
}
