package dream.work.list.form;

import common.struts.BaseForm;
import dream.work.list.dto.MaWoResultClnListDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * 작업설비 목록
 * @author  kim21017
 * @version $Id: MaWoResultClnListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maWoResultClnListForm"
 */
public class MaWoResultClnListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();
    /**  작업설비 목록  */
    private MaWoResultClnListDTO maWoResultClnListDTO = new MaWoResultClnListDTO();
	

	public MaWoResultMstrCommonDTO getMaWoResultMstrCommonDTO() {
		return maWoResultMstrCommonDTO;
	}

	public void setMaWoResultMstrCommonDTO(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) {
		this.maWoResultMstrCommonDTO = maWoResultMstrCommonDTO;
	}

	public MaWoResultClnListDTO getMaWoResultClnListDTO() {
		return maWoResultClnListDTO;
	}

	public void setMaWoResultClnListDTO(MaWoResultClnListDTO maWoResultClnListDTO) {
		this.maWoResultClnListDTO = maWoResultClnListDTO;
	}
}
