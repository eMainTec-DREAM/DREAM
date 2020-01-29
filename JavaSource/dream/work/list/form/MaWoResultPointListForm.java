package dream.work.list.form;

import common.struts.BaseForm;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultPointListDTO;

/**
 * 작업결과 검사항목 목록
 * @author  kim21017
 * @version $Id: MaWoResultPointListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maWoResultPointListForm"
 */
public class MaWoResultPointListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();
    /**  작업결과 검사항목 목록  */
    private MaWoResultPointListDTO maWoResultPointListDTO = new MaWoResultPointListDTO();
	

	public MaWoResultMstrCommonDTO getMaWoResultMstrCommonDTO() {
		return maWoResultMstrCommonDTO;
	}

	public void setMaWoResultMstrCommonDTO(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) {
		this.maWoResultMstrCommonDTO = maWoResultMstrCommonDTO;
	}

	public MaWoResultPointListDTO getMaWoResultPointListDTO() {
		return maWoResultPointListDTO;
	}

	public void setMaWoResultPointListDTO(MaWoResultPointListDTO maWoResultPointListDTO) {
		this.maWoResultPointListDTO = maWoResultPointListDTO;
	}
}
