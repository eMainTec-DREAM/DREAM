package dream.work.list.form;

import common.struts.BaseForm;
import dream.work.list.dto.MaWoResultCraftDetailDTO;
import dream.work.list.dto.MaWoResultCraftListDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * �۾���� �۾���
 * @author  kim2107
 * @version $Id: MaWoResultCraftDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="maWoResultCraftDetailForm"
 */
public class MaWoResultCraftDetailForm extends BaseForm
{    
    //===============================================================
    /** ���� DTO */
    private MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();
	/** �۾���� �۾��� ��� DTO  */
    private MaWoResultCraftListDTO maWoResultCraftListDTO = new MaWoResultCraftListDTO();
	/** �۾���� �۾��� �� DTO  */
    private MaWoResultCraftDetailDTO maWoResultCraftDetailDTO = new MaWoResultCraftDetailDTO();
    
	public MaWoResultCraftListDTO getMaWoResultCraftListDTO() {
		return maWoResultCraftListDTO;
	}
	public void setMaWoResultCraftListDTO(MaWoResultCraftListDTO maWoResultCraftListDTO) {
		this.maWoResultCraftListDTO = maWoResultCraftListDTO;
	}
	public MaWoResultCraftDetailDTO getMaWoResultCraftDetailDTO() {
		return maWoResultCraftDetailDTO;
	}
	public void setMaWoResultCraftDetailDTO(MaWoResultCraftDetailDTO maWoResultCraftDetailDTO) {
		this.maWoResultCraftDetailDTO = maWoResultCraftDetailDTO;
	}
	public MaWoResultMstrCommonDTO getMaWoResultMstrCommonDTO() {
		return maWoResultMstrCommonDTO;
	}
	public void setMaWoResultMstrCommonDTO(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) {
		this.maWoResultMstrCommonDTO = maWoResultMstrCommonDTO;
	}
}
