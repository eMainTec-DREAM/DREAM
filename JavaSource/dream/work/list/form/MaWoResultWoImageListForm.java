package dream.work.list.form;

import common.struts.BaseForm;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultWoImageListDTO;

/**
 * �۾���� �۾����� FORM
 * @author  kim21017
 * @version $Id: MaWoResultWoImageListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maWoResultWoImageListForm"
 */
public class MaWoResultWoImageListForm extends BaseForm
{    
    //===============================================================
    /** �۾� ��� ���� */
    private MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();
    /**  �۾���� �۾����� ���  */
    private MaWoResultWoImageListDTO maWoResultWoImageListDTO = new MaWoResultWoImageListDTO();
    
	public MaWoResultMstrCommonDTO getMaWoResultMstrCommonDTO() {
		return maWoResultMstrCommonDTO;
	}
	public void setMaWoResultMstrCommonDTO(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) {
		this.maWoResultMstrCommonDTO = maWoResultMstrCommonDTO;
	}
	public MaWoResultWoImageListDTO getMaWoResultWoImageListDTO() {
		return maWoResultWoImageListDTO;
	}
	public void setMaWoResultWoImageListDTO(MaWoResultWoImageListDTO maWoResultWoImageListDTO) {
		this.maWoResultWoImageListDTO = maWoResultWoImageListDTO;
	}

}
