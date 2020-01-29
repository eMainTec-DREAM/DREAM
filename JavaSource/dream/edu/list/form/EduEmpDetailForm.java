package dream.edu.list.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.edu.list.dto.EduCommonDTO;
import dream.edu.list.dto.EduEmpDetailDTO;
import dream.edu.list.dto.EduEmpListDTO;

/**
 * �۾���� �۾���
 * @author  kim2107
 * @version $Id: EduEmpDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="eduEmpDetailForm"
 */
public class EduEmpDetailForm extends BaseForm
{
    //===============================================================
    /** ���� DTO */
    private EduCommonDTO eduCommonDTO = new EduCommonDTO();
	/** �۾���� �۾��� ��� DTO  */
    private EduEmpListDTO eduEmpListDTO = new EduEmpListDTO();
	/** �۾���� �۾��� �� DTO  */
    private EduEmpDetailDTO eduEmpDetailDTO = new EduEmpDetailDTO();
    /** ÷�ι��� DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();
    
	public MaDocLibCommonDTO getMaDocLibCommonDTO() {
		return maDocLibCommonDTO;
	}
	public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO) {
		this.maDocLibCommonDTO = maDocLibCommonDTO;
	}
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
	public EduEmpDetailDTO getEduEmpDetailDTO() {
		return eduEmpDetailDTO;
	}
	public void setEduEmpDetailDTO(EduEmpDetailDTO eduEmpDetailDTO) {
		this.eduEmpDetailDTO = eduEmpDetailDTO;
	}

}
