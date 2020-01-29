package dream.doc.notice.form;

import common.struts.BaseForm;
import dream.doc.notice.dto.DocNoticeCommonDTO;
import dream.doc.notice.dto.DocNoticeTargetListDTO;

/**
 * DocNoticeTarget Page - List Form
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @struts.form name="docNoticeTargetListForm"
 * */
public class DocNoticeTargetListForm extends BaseForm {
    
    private DocNoticeCommonDTO docNoticeCommonDTO = new DocNoticeCommonDTO();
    private DocNoticeTargetListDTO docNoticeTargetListDTO = new DocNoticeTargetListDTO();

    
    public DocNoticeCommonDTO getDocNoticeCommonDTO()
    {
        return docNoticeCommonDTO;
    }

    public void setDocNoticeCommonDTO(DocNoticeCommonDTO docNoticeCommonDTO)
    {
        this.docNoticeCommonDTO = docNoticeCommonDTO;
    }

    public DocNoticeTargetListDTO getDocNoticeTargetListDTO() {
        return docNoticeTargetListDTO;
    }

    public void setDocNoticeTargetListDTO(DocNoticeTargetListDTO docNoticeTargetListDTO) {
        this.docNoticeTargetListDTO = docNoticeTargetListDTO;
    }
}