package dream.doc.ctg.form;

import common.struts.BaseForm;
import dream.doc.ctg.dto.DocCtgCommonDTO;
import dream.doc.ctg.dto.DocCtgDetailDTO;

/**
 * �����з�ü��- �� Form
 * @author  ssong
 * @version $Id: DocCtgDetailForm.java,v 1.0 2015/12/02 09:13:09 ssong Exp $
 * @since   1.0
 *
 * @struts.form name="docCtgDetailForm"
 */
public class DocCtgDetailForm extends BaseForm
{
    //========================================================================
    /** ���� */ 
    private DocCtgCommonDTO docCtgCommonDTO = new DocCtgCommonDTO();
    //========================================================================
    /** �� */
    private DocCtgDetailDTO docCtgDetailDTO = new DocCtgDetailDTO();


    public DocCtgDetailDTO getDocCtgDetailDTO() {
		return docCtgDetailDTO;
	}

	public void setDocCtgDetailDTO(DocCtgDetailDTO docCtgDetailDTO) {
		this.docCtgDetailDTO = docCtgDetailDTO;
	}

	public DocCtgCommonDTO getDocCtgCommonDTO() {
		return docCtgCommonDTO;
	}

    public void setDocCtgCommonDTO(DocCtgCommonDTO docCtgCommonDTO) {
		this.docCtgCommonDTO = docCtgCommonDTO;
	}
}
