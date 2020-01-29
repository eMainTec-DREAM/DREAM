package dream.doc.notice.form;

import common.struts.BaseForm;
import dream.doc.notice.dto.DocNoticeCommonDTO;
import dream.doc.notice.dto.DocNoticeDeptDetailDTO;
import dream.doc.notice.dto.DocNoticeDeptListDTO;

/**
 * DocNoticeDept - Detail Form
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @struts.form name="docNoticeDeptDetailForm"
 */
public class DocNoticeDeptDetailForm extends BaseForm
{
	private DocNoticeCommonDTO docNoticeCommonDTO = new DocNoticeCommonDTO();
	private DocNoticeDeptListDTO docNoticeDeptListDTO = new DocNoticeDeptListDTO();
	private DocNoticeDeptDetailDTO docNoticeDeptDetailDTO = new DocNoticeDeptDetailDTO();
	
	public DocNoticeCommonDTO getDocNoticeCommonDTO() {
		return docNoticeCommonDTO;
	}
	public void setDocNoticeCommonDTO(DocNoticeCommonDTO docNoticeCommonDTO) {
		this.docNoticeCommonDTO = docNoticeCommonDTO;
	}
	public DocNoticeDeptListDTO getDocNoticeDeptListDTO() {
		return docNoticeDeptListDTO;
	}
	public void setDocNoticeDeptListDTO(DocNoticeDeptListDTO docNoticeDeptListDTO) {
		this.docNoticeDeptListDTO = docNoticeDeptListDTO;
	}
	public DocNoticeDeptDetailDTO getDocNoticeDeptDetailDTO() {
		return docNoticeDeptDetailDTO;
	}
	public void setDocNoticeDeptDetailDTO(DocNoticeDeptDetailDTO docNoticeDeptDetailDTO) {
		this.docNoticeDeptDetailDTO = docNoticeDeptDetailDTO;
	}
}
