package dream.doc.manual.form;

import common.struts.BaseForm;
import dream.doc.manual.dto.DocManualCommonDTO;

/**
 * ����ڸŴ��� - ��� form
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="docManualListForm"
 */
public class DocManualListForm extends BaseForm
{    
    //===============================================================
    /** ȭ�� ���� */
    private DocManualCommonDTO docManualCommonDTO = new DocManualCommonDTO();
    
	public DocManualCommonDTO getDocManualCommonDTO() {
		return docManualCommonDTO;
	}

	public void setDocManualCommonDTO(DocManualCommonDTO docManualCommonDTO) {
		this.docManualCommonDTO = docManualCommonDTO;
	}
}
