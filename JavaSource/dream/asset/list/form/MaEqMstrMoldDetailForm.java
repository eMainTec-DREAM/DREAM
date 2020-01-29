package dream.asset.list.form;

import common.struts.BaseForm;

import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrDetailDTO;
import dream.asset.list.dto.MaEqMstrMoldDetailDTO;
import dream.doc.file.dto.MaDocLibCommonDTO;

/**
 * 설비마스터- 상세 Form
 * @author  kim21017
 * @version $Id: MaEqMstrDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maEqMstrMoldDetailForm"
 */
public class MaEqMstrMoldDetailForm extends BaseForm
{
    //========================================================================
    /** 설비마스터 공통 */ 
    private MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
    //========================================================================
    /** 설비마스터 상세 */
    private MaEqMstrDetailDTO maEqMstrDetailDTO = new MaEqMstrDetailDTO();
    /** 설비금형 상세 */
    private MaEqMstrMoldDetailDTO maEqMstrMoldDetailDTO = new MaEqMstrMoldDetailDTO();
    /** 첨부문서 DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();

	public MaDocLibCommonDTO getMaDocLibCommonDTO()
    {
        return maDocLibCommonDTO;
    }

    public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO)
    {
        this.maDocLibCommonDTO = maDocLibCommonDTO;
    }

    public MaEqMstrCommonDTO getMaEqMstrCommonDTO() {
		return maEqMstrCommonDTO;
	}

    public void setMaEqMstrCommonDTO(MaEqMstrCommonDTO maEqMstrCommonDTO) {
		this.maEqMstrCommonDTO = maEqMstrCommonDTO;
	}

	public MaEqMstrDetailDTO getMaEqMstrDetailDTO() {
		return maEqMstrDetailDTO;
	}

	public void setMaEqMstrDetailDTO(MaEqMstrDetailDTO maEqMstrDetailDTO) {
		this.maEqMstrDetailDTO = maEqMstrDetailDTO;
	}
	
	public MaEqMstrMoldDetailDTO getMaEqMstrMoldDetailDTO() {
		return maEqMstrMoldDetailDTO;
	}

	public void setMaEqMstrMoldDetailDTO(MaEqMstrMoldDetailDTO maEqMstrMoldDetailDTO) {
		this.maEqMstrMoldDetailDTO = maEqMstrMoldDetailDTO;
	}
	
}
