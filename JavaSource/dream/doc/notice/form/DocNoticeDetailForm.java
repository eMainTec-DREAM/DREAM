package dream.doc.notice.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.doc.notice.dto.DocNoticeCommonDTO;
import dream.doc.notice.dto.DocNoticeDetailDTO;

/**
 * DocNotice Page - Detail Form
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @struts.form name="docNoticeDetailForm"
 */
public class DocNoticeDetailForm extends BaseForm
{
    private DocNoticeCommonDTO docNoticeCommonDTO = new DocNoticeCommonDTO();
    private DocNoticeDetailDTO docNoticeDetailDTO = new DocNoticeDetailDTO();
    
    /** Ã·ºÎ¹®¼­ DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();
    
	public DocNoticeCommonDTO getDocNoticeCommonDTO() {
        return docNoticeCommonDTO;
    }
    public MaDocLibCommonDTO getMaDocLibCommonDTO() {
		return maDocLibCommonDTO;
	}
	public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO) {
		this.maDocLibCommonDTO = maDocLibCommonDTO;
	}
	public void setDocNoticeCommonDTO(DocNoticeCommonDTO docNoticeCommonDTO) {
        this.docNoticeCommonDTO = docNoticeCommonDTO;
    }
    public DocNoticeDetailDTO getDocNoticeDetailDTO() {
        return docNoticeDetailDTO;
    }
    public void setDocNoticeDetailDTO(DocNoticeDetailDTO docNoticeDetailDTO) {
        this.docNoticeDetailDTO = docNoticeDetailDTO;
    }
}
