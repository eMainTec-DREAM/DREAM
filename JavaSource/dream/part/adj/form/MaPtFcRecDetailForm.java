package dream.part.adj.form;

import common.struts.BaseForm;
import dream.part.adj.dto.MaPtFcRecCommonDTO;
import dream.part.adj.dto.MaPtFcRecDetailDTO;

/**
 * �����԰�- �� Form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maPtFcRecDetailForm"
 */
public class MaPtFcRecDetailForm extends BaseForm
{
    //========================================================================
    /** �����԰� ���� */ 
    private MaPtFcRecCommonDTO maPtFcRecCommonDTO = new MaPtFcRecCommonDTO();
    //========================================================================
    /** �����԰� �� */
    private MaPtFcRecDetailDTO maPtFcRecDetailDTO = new MaPtFcRecDetailDTO();
    
	public MaPtFcRecCommonDTO getMaPtFcRecCommonDTO() {
		return maPtFcRecCommonDTO;
	}
	public void setMaPtFcRecCommonDTO(MaPtFcRecCommonDTO maPtFcRecCommonDTO) {
		this.maPtFcRecCommonDTO = maPtFcRecCommonDTO;
	}
	public MaPtFcRecDetailDTO getMaPtFcRecDetailDTO() {
		return maPtFcRecDetailDTO;
	}
	public void setMaPtFcRecDetailDTO(MaPtFcRecDetailDTO maPtFcRecDetailDTO) {
		this.maPtFcRecDetailDTO = maPtFcRecDetailDTO;
	}
    
}
