package dream.edu.list.form;

import common.struts.BaseForm;
import dream.edu.list.dto.EduCommonDTO;
import dream.edu.list.dto.EduDetailDTO;

/**
 * �ڰ����з�- �� Form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="eduDetailForm"
 */
public class EduDetailForm extends BaseForm
{
    //========================================================================
    /** �ڰ����з� ���� */ 
    private EduCommonDTO eduCommonDTO = new EduCommonDTO();
    //========================================================================
    /** �ڰ����з� �� */
    private EduDetailDTO eduDetailDTO = new EduDetailDTO();

    public EduCommonDTO getEduCommonDTO() {
		return eduCommonDTO;
	}

    public void setEduCommonDTO(EduCommonDTO eduCommonDTO) {
		this.eduCommonDTO = eduCommonDTO;
	}

	public EduDetailDTO getEduDetailDTO() {
		return eduDetailDTO;
	}

	public void setEduDetailDTO(EduDetailDTO eduDetailDTO) {
		this.eduDetailDTO = eduDetailDTO;
	}
}
