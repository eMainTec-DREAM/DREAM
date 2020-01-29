package dream.work.list.form;

import common.struts.BaseForm;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultMstrDetailDTO;
import dream.work.list.dto.MaWoResultToolDetailDTO;
import dream.work.list.dto.MaWoResultToolListDTO;

/**
 * �۾���� ���԰��ⱸ
 * @author  kim2107
 * @version $Id: MaWoResultToolDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="maWoResultToolDetailForm"
 */
public class MaWoResultToolDetailForm extends BaseForm
{    
    //===============================================================
    /** ���� DTO */
    private MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();
    /** �� */
    private MaWoResultMstrDetailDTO maWoResultMstrDetailDTO = new MaWoResultMstrDetailDTO();
    
	/** �۾���� ���԰��ⱸ ��� DTO  */
    private MaWoResultToolListDTO maWoResultToolListDTO = new MaWoResultToolListDTO();
	/** �۾���� ���԰��ⱸ �� DTO  */
    private MaWoResultToolDetailDTO maWoResultToolDetailDTO = new MaWoResultToolDetailDTO();
    
	public MaWoResultToolListDTO getMaWoResultToolListDTO() {
		return maWoResultToolListDTO;
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
    public void setMaWoResultToolListDTO(MaWoResultToolListDTO maWoResultToolListDTO) {
		this.maWoResultToolListDTO = maWoResultToolListDTO;
	}
	public MaWoResultToolDetailDTO getMaWoResultToolDetailDTO() {
		return maWoResultToolDetailDTO;
	}
	public void setMaWoResultToolDetailDTO(MaWoResultToolDetailDTO maWoResultToolDetailDTO) {
		this.maWoResultToolDetailDTO = maWoResultToolDetailDTO;
	}
	public MaWoResultMstrCommonDTO getMaWoResultMstrCommonDTO() {
		return maWoResultMstrCommonDTO;
	}
	public void setMaWoResultMstrCommonDTO(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) {
		this.maWoResultMstrCommonDTO = maWoResultMstrCommonDTO;
	}
}
