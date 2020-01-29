package dream.tool.adj.form;

import common.struts.BaseForm;
import dream.tool.adj.dto.MaPttDisCommonDTO;

/**
 * 목록 form
 * @author  kim21017
 * @version $Id: MaPttDisListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maPttDisListForm"
 */
public class MaPttDisListForm extends BaseForm
{    
    //===============================================================
    /** 질의 공통 */
    private MaPttDisCommonDTO maPttDisCommonDTO = new MaPttDisCommonDTO();
    
	public MaPttDisCommonDTO getMaPttDisCommonDTO() {
		return maPttDisCommonDTO;
	}

	public void setMaPttDisCommonDTO(MaPttDisCommonDTO maPttDisCommonDTO) {
		this.maPttDisCommonDTO = maPttDisCommonDTO;
	}
}
