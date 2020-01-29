package dream.doc.notice.form;

import common.struts.BaseForm;
import dream.doc.notice.dto.DocNoticeCommonDTO;
import dream.doc.notice.dto.DocNoticeDeptListDTO;

/**
 * DocNoticeDept Page - List Form
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @struts.form name="docNoticeDeptListForm"
 * */
public class DocNoticeDeptListForm extends BaseForm {
    
    private DocNoticeCommonDTO docNoticeCommonDTO = new DocNoticeCommonDTO();
    private DocNoticeDeptListDTO docNoticeDeptListDTO = new DocNoticeDeptListDTO();

    
    public DocNoticeCommonDTO getDocNoticeCommonDTO()
    {
        return docNoticeCommonDTO;
    }

    public void setDocNoticeCommonDTO(DocNoticeCommonDTO docNoticeCommonDTO)
    {
        this.docNoticeCommonDTO = docNoticeCommonDTO;
    }

    public DocNoticeDeptListDTO getDocNoticeDeptListDTO() {
        return docNoticeDeptListDTO;
    }

    public void setDocNoticeDeptListDTO(DocNoticeDeptListDTO docNoticeDeptListDTO) {
        this.docNoticeDeptListDTO = docNoticeDeptListDTO;
    }
}