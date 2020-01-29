package dream.asset.list.form;

import common.struts.BaseForm;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrDetailDTO;
import dream.asset.list.dto.MaEqMstrPartDetailDTO;
import dream.asset.list.dto.MaEqMstrPartListDTO;

/**
 * 구성자재 목록
 * @author  kim21017
 * @version $Id: MaEqMstrPartListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maEqMstrPartListForm"
 */
public class MaEqMstrPartListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
    /**  구성자재 목록  */
    private MaEqMstrPartListDTO maEqMstrPartListDTO = new MaEqMstrPartListDTO();
    /** 구성자재 상세 DTO  */
    private MaEqMstrPartDetailDTO maEqMstrPartDetailDTO = new MaEqMstrPartDetailDTO();
	
    private MaEqMstrDetailDTO maEqMstrDetailDTO = new MaEqMstrDetailDTO();

    
	public MaEqMstrPartDetailDTO getMaEqMstrPartDetailDTO()
    {
        return maEqMstrPartDetailDTO;
    }

    public void setMaEqMstrPartDetailDTO(
            MaEqMstrPartDetailDTO maEqMstrPartDetailDTO)
    {
        this.maEqMstrPartDetailDTO = maEqMstrPartDetailDTO;
    }

    public MaEqMstrDetailDTO getMaEqMstrDetailDTO()
    {
        return maEqMstrDetailDTO;
    }

    public void setMaEqMstrDetailDTO(MaEqMstrDetailDTO maEqMstrDetailDTO)
    {
        this.maEqMstrDetailDTO = maEqMstrDetailDTO;
    }

    public MaEqMstrCommonDTO getMaEqMstrCommonDTO() {
		return maEqMstrCommonDTO;
	}

	public void setMaEqMstrCommonDTO(MaEqMstrCommonDTO maEqMstrCommonDTO) {
		this.maEqMstrCommonDTO = maEqMstrCommonDTO;
	}

	public MaEqMstrPartListDTO getMaEqMstrPartListDTO() {
		return maEqMstrPartListDTO;
	}

	public void setMaEqMstrPartListDTO(MaEqMstrPartListDTO maEqMstrPartListDTO) {
		this.maEqMstrPartListDTO = maEqMstrPartListDTO;
	}
}
