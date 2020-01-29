package dream.tool.adj.form;

import common.struts.BaseForm;
import dream.tool.adj.dto.MaPttDisCommonDTO;
import dream.tool.adj.dto.MaPttDisDetailDTO;
import dream.tool.adj.dto.MaPttDisPartsListDTO;

/**
 * 목록
 * @author  kim21017
 * @version $Id: MaPttDisPartsListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maPttDisPartsListForm"
 */
public class MaPttDisPartsListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private MaPttDisCommonDTO maPttDisCommonDTO = new MaPttDisCommonDTO();
    /**   */
    private MaPttDisPartsListDTO maPttDisPartsListDTO = new MaPttDisPartsListDTO();
    
    private MaPttDisDetailDTO maPttDisDetailDTO = new MaPttDisDetailDTO();
    
	public MaPttDisCommonDTO getMaPttDisCommonDTO() {
		return maPttDisCommonDTO;
	}

	public void setMaPttDisCommonDTO(MaPttDisCommonDTO maPttDisCommonDTO) {
		this.maPttDisCommonDTO = maPttDisCommonDTO;
	}

	public MaPttDisPartsListDTO getMaPttDisPartsListDTO() {
		return maPttDisPartsListDTO;
	}

	public void setMaPttDisPartsListDTO(MaPttDisPartsListDTO maPttDisPartsListDTO) {
		this.maPttDisPartsListDTO = maPttDisPartsListDTO;
	}

	public MaPttDisDetailDTO getMaPttDisDetailDTO() {
		return maPttDisDetailDTO;
	}

	public void setMaPttDisDetailDTO(MaPttDisDetailDTO maPttDisDetailDTO) {
		this.maPttDisDetailDTO = maPttDisDetailDTO;
	}
	
}
