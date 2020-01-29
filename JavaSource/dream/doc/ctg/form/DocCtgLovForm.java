package dream.doc.ctg.form;

import dream.comm.form.MaFinderAcForm;
import dream.doc.ctg.dto.DocCtgCommonDTO;
import dream.doc.ctg.dto.DocCtgLovDTO;


/**
 * 문서분류체계 - 목록 form
 * @author  ssong
 * @version $Id: DocCtgListForm.java,v 1.0 2015/12/01 09:13:09 ssong Exp $
 * @since   1.0
 *
 * @struts.form name="docCtgLovForm"
 */
public class DocCtgLovForm extends MaFinderAcForm
{    
    //===============================================================
	/** 공통 */
	private DocCtgCommonDTO docCtgCommonDTO = new DocCtgCommonDTO();
    /** 문서분류팝업 */
    private DocCtgLovDTO docCtgLovDTO = new DocCtgLovDTO();
    
	public DocCtgCommonDTO getDocCtgCommonDTO() {
		return docCtgCommonDTO;
	}

	public void setDocCtgCommonDTO(DocCtgCommonDTO docCtgCommonDTO) {
		this.docCtgCommonDTO = docCtgCommonDTO;
	}
	
	public DocCtgLovDTO getDocCtgLovDTO() {
		return docCtgLovDTO;
	}

	public void setDocCtgLovDTO(DocCtgLovDTO docCtgLovDTO) {
		this.docCtgLovDTO = docCtgLovDTO;
	}
}
