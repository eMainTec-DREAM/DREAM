package dream.doc.ctg.form;

import common.struts.BaseForm;
import dream.doc.ctg.dto.DocCtgCommonDTO;


/**
 * �����з�ü�� - ��� form
 * @author  ssong
 * @version $Id: DocCtgListForm.java,v 1.0 2015/12/01 09:13:09 ssong Exp $
 * @since   1.0
 *
 * @struts.form name="docCtgListForm"
 */
public class DocCtgListForm extends BaseForm
{    
    //===============================================================
    /** ���� */
    private DocCtgCommonDTO docCtgCommonDTO = new DocCtgCommonDTO();
    
	public DocCtgCommonDTO getDocCtgCommonDTO() {
		return docCtgCommonDTO;
	}

	public void setDocCtgCommonDTO(DocCtgCommonDTO docCtgCommonDTO) {
		this.docCtgCommonDTO = docCtgCommonDTO;
	}
}
