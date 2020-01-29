package dream.work.list.form;

import common.struts.BaseForm;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultPointListDTO;
import dream.work.list.dto.MaWoResultStPointListDTO;

/**
 * �۾���� �۾��ʼ��˻��׸� ���
 * @author  kim21017
 * @version $Id: MaWoResultStPointListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maWoResultStPointListForm"
 */
public class MaWoResultStPointListForm extends BaseForm
{    
    //===============================================================
    /** ���� */
    private MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();
    /**  �۾���� �۾��ʼ��˻��׸� ���  */
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
