package dream.doc.notice.form;

import common.struts.BaseForm;
import dream.doc.notice.dto.DocNoticeCommonDTO;


/**
 * DocNotice Page - List Form
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @struts.form name="docNoticeListForm"
 * */
public class DocNoticeListForm extends BaseForm {
    
    private DocNoticeCommonDTO docNoticeCommonDTO = new DocNoticeCommonDTO();
    
	public DocNoticeCommonDTO getDocNoticeCommonDTO() {
        return docNoticeCommonDTO;
    }

    public void setDocNoticeCommonDTO(DocNoticeCommonDTO docNoticeCommonDTO) {
        this.docNoticeCommonDTO = docNoticeCommonDTO;
    }
}