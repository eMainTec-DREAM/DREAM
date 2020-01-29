package dream.work.list.form;

import common.struts.BaseForm;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultMstrDetailDTO;
import dream.work.list.dto.MaWoResultToolDetailDTO;
import dream.work.list.dto.MaWoResultToolListDTO;

/**
 * 작업결과 투입공기구 목록
 * @author  kim21017
 * @version $Id: MaWoResultToolListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maWoResultToolListForm"
 */
public class MaWoResultToolListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();
    /** 상세 */
    private MaWoResultMstrDetailDTO maWoResultMstrDetailDTO = new MaWoResultMstrDetailDTO();
    /**  작업결과 투입공기구 목록  */
    private MaWoResultToolListDTO maWoResultToolListDTO = new MaWoResultToolListDTO();
    /**  작업결과 투입공기구 상세  */
    private MaWoResultToolDetailDTO maWoResultToolDetailDTO = new MaWoResultToolDetailDTO();
	

	public MaWoResultMstrDetailDTO getMaWoResultMstrDetailDTO()
    {
        return maWoResultMstrDetailDTO;
    }

    public void setMaWoResultMstrDetailDTO(
            MaWoResultMstrDetailDTO maWoResultMstrDetailDTO)
    {
        this.maWoResultMstrDetailDTO = maWoResultMstrDetailDTO;
    }

    public MaWoResultToolDetailDTO getMaWoResultToolDetailDTO()
    {
        return maWoResultToolDetailDTO;
    }

    public void setMaWoResultToolDetailDTO(
            MaWoResultToolDetailDTO maWoResultToolDetailDTO)
    {
        this.maWoResultToolDetailDTO = maWoResultToolDetailDTO;
    }

    public MaWoResultMstrCommonDTO getMaWoResultMstrCommonDTO() {
		return maWoResultMstrCommonDTO;
	}

	public void setMaWoResultMstrCommonDTO(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) {
		this.maWoResultMstrCommonDTO = maWoResultMstrCommonDTO;
	}

	public MaWoResultToolListDTO getMaWoResultToolListDTO() {
		return maWoResultToolListDTO;
	}

	public void setMaWoResultToolListDTO(MaWoResultToolListDTO maWoResultToolListDTO) {
		this.maWoResultToolListDTO = maWoResultToolListDTO;
	}
}
