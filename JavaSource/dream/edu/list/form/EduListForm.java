package dream.edu.list.form;

import common.struts.BaseForm;
import dream.edu.list.dto.EduCommonDTO;

/**
 * 자격증분류 - 목록 form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="eduListForm"
 */
public class EduListForm extends BaseForm
{    
    //===============================================================
    /** 자격증분류 공통 */
    private EduCommonDTO eduCommonDTO = new EduCommonDTO();
    
	public EduCommonDTO getEduCommonDTO() {
		return eduCommonDTO;
	}

	public void setEduCommonDTO(EduCommonDTO eduCommonDTO) {
		this.eduCommonDTO = eduCommonDTO;
	}
}
