package dream.doc.notice.form;

import common.struts.BaseForm;
import dream.doc.notice.dto.DocNoticeCommonDTO;
import dream.doc.notice.dto.DocNoticeTargetDetailDTO;
import dream.doc.notice.dto.DocNoticeTargetListDTO;

/**
 * DocNoticeTarget - Detail Form
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @struts.form name="docNoticeTargetDetailForm"
 */
public class DocNoticeTargetDetailForm extends BaseForm
{
	private DocNoticeCommonDTO docNoticeCommonDTO = new DocNoticeCommonDTO();
	private DocNoticeTargetListDTO docNoticeTargetListDTO = new DocNoticeTargetListDTO();
	private DocNoticeTargetDetailDTO docNoticeTargetDetailDTO = new DocNoticeTargetDetailDTO();
	
	public DocNoticeCommonDTO getDocNoticeCommonDTO() {
		return docNoticeCommonDTO;
	}
	public void setDocNoticeCommonDTO(DocNoticeCommonDTO docNoticeCommonDTO) {
		this.docNoticeCommonDTO = docNoticeCommonDTO;
	}
	public DocNoticeTargetListDTO getDocNoticeTargetListDTO() {
		return docNoticeTargetListDTO;
	}
	public void setDocNoticeTargetListDTO(DocNoticeTargetListDTO docNoticeTargetListDTO) {
		this.docNoticeTargetListDTO = docNoticeTargetListDTO;
	}
	public DocNoticeTargetDetailDTO getDocNoticeTargetDetailDTO() {
		return docNoticeTargetDetailDTO;
	}
	public void setDocNoticeTargetDetailDTO(DocNoticeTargetDetailDTO docNoticeTargetDetailDTO) {
		this.docNoticeTargetDetailDTO = docNoticeTargetDetailDTO;
	}
}
