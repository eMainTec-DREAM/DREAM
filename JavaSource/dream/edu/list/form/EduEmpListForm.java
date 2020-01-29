package dream.edu.list.form;

import common.struts.BaseForm;

import dream.edu.list.dto.EduCommonDTO;
import dream.edu.list.dto.EduEmpListDTO;

/**
 * �ڰ��� ��� ���
 * @author  kim21017
 * @version $Id: EduEmpListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="eduEmpListForm"
 */
public class EduEmpListForm extends BaseForm
{
    //===============================================================
    /** ���� */
    private EduCommonDTO eduCommonDTO = new EduCommonDTO();
    /**  �ڰ��� ��� ���  */
    private EduEmpListDTO eduEmpListDTO = new EduEmpListDTO();
    
    
	public EduCommonDTO getEduCommonDTO() {
		return eduCommonDTO;
	}
	public void setEduCommonDTO(EduCommonDTO eduCommonDTO) {
		this.eduCommonDTO = eduCommonDTO;
	}
	public EduEmpListDTO getEduEmpListDTO() {
		return eduEmpListDTO;
	}
	public void setEduEmpListDTO(EduEmpListDTO eduEmpListDTO) {
		this.eduEmpListDTO = eduEmpListDTO;
	}

	


}
