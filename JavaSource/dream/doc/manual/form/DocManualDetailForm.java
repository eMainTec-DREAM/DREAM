package dream.doc.manual.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.doc.manual.dto.DocManualCommonDTO;
import dream.doc.manual.dto.DocManualDetailDTO;

/**
 * ����ڸŴ���- �� Form
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="docManualDetailForm"
 */
public class DocManualDetailForm extends BaseForm
{
    //========================================================================
    /** ȭ�� ���� */ 
    private DocManualCommonDTO docManualCommonDTO = new DocManualCommonDTO();
    //========================================================================
    /** ȭ�� �� */
    private DocManualDetailDTO docManualDetailDTO = new DocManualDetailDTO();
    /** ÷�ι��� DTO */
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
