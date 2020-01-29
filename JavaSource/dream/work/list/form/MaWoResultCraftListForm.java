package dream.work.list.form;

import common.struts.BaseForm;
import dream.work.list.dto.MaWoResultCraftDetailDTO;
import dream.work.list.dto.MaWoResultCraftListDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * �۾���� �۾��� ���
 * @author  kim21017
 * @version $Id: MaWoResultCraftListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maWoResultCraftListForm"
 */
public class MaWoResultCraftListForm extends BaseForm
{    
    //===============================================================
    /** ���� */
    private MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();
    /**  �۾���� �۾��� ���  */
    private MaWoResultCraftListDTO maWoResultCraftListDTO = new MaWoResultCraftListDTO();
    /** �۾���� �۾��� �� DTO  */
    private MaWoResultCraftDetailDTO maWoResultCraftDetailDTO = new MaWoResultCraftDetailDTO();
	

	public MaWoResultCraftDetailDTO getMaWoResultCraftDetailDTO()
    {
        return maWoResultCraftDetailDTO;
    }

    public void setMaWoResultCraftDetailDTO(
            MaWoResultCraftDetailDTO maWoResultCraftDetailDTO)
    {
        this.maWoResultCraftDetailDTO = maWoResultCraftDetailDTO;
    }

    public MaWoResultMstrCommonDTO getMaWoResultMstrCommonDTO() {
		return maWoResultMstrCommonDTO;
	}

	public void setMaWoResultMstrCommonDTO(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) {
		this.maWoResultMstrCommonDTO = maWoResultMstrCommonDTO;
	}

	public MaWoResultCraftListDTO getMaWoResultCraftListDTO() {
		return maWoResultCraftListDTO;
	}

	public void setMaWoResultCraftListDTO(MaWoResultCraftListDTO maWoResultCraftListDTO) {
		this.maWoResultCraftListDTO = maWoResultCraftListDTO;
	}
}
