package dream.doc.manual.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.doc.manual.dto.DocManualCommonDTO;
import dream.doc.manual.dto.DocManualDetailDTO;

/**
 * 사용자매뉴얼- 상세 Form
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="docManualDetailForm"
 */
public class DocManualDetailForm extends BaseForm
{
    //========================================================================
    /** 화면 공통 */ 
    private DocManualCommonDTO docManualCommonDTO = new DocManualCommonDTO();
    //========================================================================
    /** 화면 상세 */
    private DocManualDetailDTO docManualDetailDTO = new DocManualDetailDTO();
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

    public DocManualCommonDTO getDocManualCommonDTO() {
		return docManualCommonDTO;
	}

    public void setDocManualCommonDTO(DocManualCommonDTO docManualCommonDTO) {
		this.docManualCommonDTO = docManualCommonDTO;
	}

	public DocManualDetailDTO getDocManualDetailDTO() {
		return docManualDetailDTO;
	}

	public void setDocManualDetailDTO(DocManualDetailDTO docManualDetailDTO) {
		this.docManualDetailDTO = docManualDetailDTO;
	}
}
