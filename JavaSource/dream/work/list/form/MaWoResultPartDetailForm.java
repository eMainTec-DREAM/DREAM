package dream.work.list.form;

import common.struts.BaseForm;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultMstrDetailDTO;
import dream.work.list.dto.MaWoResultPartDetailDTO;
import dream.work.list.dto.MaWoResultPartListDTO;

/**
 * �۾���� ��������
 * @author  kim2107
 * @version $Id: MaWoResultPartDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="maWoResultPartDetailForm"
 */
public class MaWoResultPartDetailForm extends BaseForm
{    
    //===============================================================
    /** ���� DTO */
    private MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();
    /** �� */
    private MaWoResultMstrDetailDTO maWoResultMstrDetailDTO = new MaWoResultMstrDetailDTO();
    
	/** �۾���� �������� ��� DTO  */
    private MaWoResultPartListDTO maWoResultPartListDTO = new MaWoResultPartListDTO();
	/** �۾���� �������� �� DTO  */
    private MaWoResultPartDetailDTO maWoResultPartDetailDTO = new MaWoResultPartDetailDTO();
    
	public MaWoResultPartListDTO getMaWoResultPartListDTO() {
		return maWoResultPartListDTO;
	}
	public MaWoResultMstrDetailDTO getMaWoResultMstrDetailDTO()
    {
        return maWoResultMstrDetailDTO;
    }
    public void setMaWoResultMstrDetailDTO(
            MaWoResultMstrDetailDTO maWoResultMstrDetailDTO)
    {
        this.maWoResultMstrDetailDTO = maWoResultMstrDetailDTO;
    }
    public void setMaWoResultPartListDTO(MaWoResultPartListDTO maWoResultPartListDTO) {
		this.maWoResultPartListDTO = maWoResultPartListDTO;
	}
	public MaWoResultPartDetailDTO getMaWoResultPartDetailDTO() {
		return maWoResultPartDetailDTO;
	}
	public void setMaWoResultPartDetailDTO(MaWoResultPartDetailDTO maWoResultPartDetailDTO) {
		this.maWoResultPartDetailDTO = maWoResultPartDetailDTO;
	}
	public MaWoResultMstrCommonDTO getMaWoResultMstrCommonDTO() {
		return maWoResultMstrCommonDTO;
	}
	public void setMaWoResultMstrCommonDTO(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) {
		this.maWoResultMstrCommonDTO = maWoResultMstrCommonDTO;
	}
}
