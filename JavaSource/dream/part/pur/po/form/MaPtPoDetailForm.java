package dream.part.pur.po.form;

import common.struts.BaseForm;
import dream.part.pur.po.dto.MaPtPoCommonDTO;
import dream.part.pur.po.dto.MaPtPoDetailDTO;

/**
 * �����̷�- �� Form
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maPtPoDetailForm"
 */
public class MaPtPoDetailForm extends BaseForm
{
    //========================================================================
    /** �����̷� ���� */ 
    private MaPtPoCommonDTO maPtPoCommonDTO = new MaPtPoCommonDTO();
    //========================================================================
    /** �����̷� �� */
    private MaPtPoDetailDTO maPtPoDetailDTO = new MaPtPoDetailDTO();
    
	public MaPtPoCommonDTO getMaPtPoCommonDTO() {
		return maPtPoCommonDTO;
	}
	public void setMaPtPoCommonDTO(MaPtPoCommonDTO maPtPoCommonDTO) {
		this.maPtPoCommonDTO = maPtPoCommonDTO;
	}
	public MaPtPoDetailDTO getMaPtPoDetailDTO() {
		return maPtPoDetailDTO;
	}
	public void setMaPtPoDetailDTO(MaPtPoDetailDTO maPtPoDetailDTO) {
		this.maPtPoDetailDTO = maPtPoDetailDTO;
	}

}
