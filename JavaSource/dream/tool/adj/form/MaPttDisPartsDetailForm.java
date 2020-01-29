package dream.tool.adj.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.tool.adj.dto.MaPttDisCommonDTO;
import dream.tool.adj.dto.MaPttDisPartsDetailDTO;
import dream.tool.adj.dto.MaPttDisPartsListDTO;

/**
 * 
 * @author  kim2107
 * @version $Id: MaPttDisPartsDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="maPttDisPartsDetailForm"
 */
public class MaPttDisPartsDetailForm extends BaseForm
{    
    //===============================================================
    /**  - °øÅë DTO */
    private MaPttDisCommonDTO maPttDisCommonDTO = new MaPttDisCommonDTO();
	/**   DTO  */
    private MaPttDisPartsListDTO maPttDisPartsListDTO = new MaPttDisPartsListDTO();
	/**   Detail DTO  */
    private MaPttDisPartsDetailDTO maPttDisPartsDetailDTO = new MaPttDisPartsDetailDTO();

	public MaPttDisPartsListDTO getMaPttDisPartsListDTO() {
		return maPttDisPartsListDTO;
	}
	public void setMaPttDisPartsListDTO(MaPttDisPartsListDTO maPttDisPartsListDTO) {
		this.maPttDisPartsListDTO = maPttDisPartsListDTO;
	}
	public MaPttDisPartsDetailDTO getMaPttDisPartsDetailDTO() {
		return maPttDisPartsDetailDTO;
	}
	public void setMaPttDisPartsDetailDTO(MaPttDisPartsDetailDTO maPttDisPartsDetailDTO) {
		this.maPttDisPartsDetailDTO = maPttDisPartsDetailDTO;
	}
	public MaPttDisCommonDTO getMaPttDisCommonDTO() {
		return maPttDisCommonDTO;
	}
	public void setMaPttDisCommonDTO(MaPttDisCommonDTO maPttDisCommonDTO) {
		this.maPttDisCommonDTO = maPttDisCommonDTO;
	}
	
}
