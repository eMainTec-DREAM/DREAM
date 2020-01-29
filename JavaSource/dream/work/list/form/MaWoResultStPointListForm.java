package dream.work.list.form;

import common.struts.BaseForm;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultPointListDTO;
import dream.work.list.dto.MaWoResultStPointListDTO;

/**
 * 작업결과 작업필수검사항목 목록
 * @author  kim21017
 * @version $Id: MaWoResultStPointListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maWoResultStPointListForm"
 */
public class MaWoResultStPointListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();
    /**  작업결과 작업필수검사항목 목록  */
    private MaWoResultStPointListDTO maWoResultStPointListDTO = new MaWoResultStPointListDTO();
	

	public MaWoResultMstrCommonDTO getMaWoResultMstrCommonDTO() {
		return maWoResultMstrCommonDTO;
	}

	public void setMaWoResultMstrCommonDTO(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) {
		this.maWoResultMstrCommonDTO = maWoResultMstrCommonDTO;
	}

	public MaWoResultStPointListDTO getMaWoResultStPointListDTO() {
		return maWoResultStPointListDTO;
	}

	public void setMaWoResultStPointListDTO(MaWoResultStPointListDTO maWoResultStPointListDTO) {
		this.maWoResultStPointListDTO = maWoResultStPointListDTO;
	}

}
