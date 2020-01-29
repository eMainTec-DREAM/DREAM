package dream.work.list.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.work.list.dto.WorkListPointDetailDTO;
import dream.work.list.dto.WorkListPointListDTO;
import dream.work.list.dto.WorkListPtrlResultCommonDTO;

/**
 * 점검내용 - Detail Form
 * @author youngjoo38
 * @version $Id: WorkListPointDetailForm.java,v 1.0 2017/08/28 12:37:40 youngjoo38 Exp $
 * @since 1.0
 * @struts.form name="workListPointDetailForm"
 */
public class WorkListPointDetailForm extends BaseForm
{
	private WorkListPtrlResultCommonDTO workListPtrlResultCommonDTO = new WorkListPtrlResultCommonDTO();
	private WorkListPointListDTO workListPointListDTO = new WorkListPointListDTO();
	private WorkListPointDetailDTO workListPointDetailDTO = new WorkListPointDetailDTO();
	
	/** 첨부문서 DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();
    
	public MaDocLibCommonDTO getMaDocLibCommonDTO() {
		return maDocLibCommonDTO;
	}
	public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO) {
		this.maDocLibCommonDTO = maDocLibCommonDTO;
	}
	public WorkListPtrlResultCommonDTO getWorkListPtrlResultCommonDTO() {
		return workListPtrlResultCommonDTO;
	}
	public void setWorkListPtrlResultCommonDTO(WorkListPtrlResultCommonDTO workListPtrlResultCommonDTO) {
		this.workListPtrlResultCommonDTO = workListPtrlResultCommonDTO;
	}
	public WorkListPointListDTO getWorkListPointListDTO() {
		return workListPointListDTO;
	}
	public void setWorkListPointListDTO(WorkListPointListDTO workListPointListDTO) {
		this.workListPointListDTO = workListPointListDTO;
	}
	public WorkListPointDetailDTO getWorkListPointDetailDTO() {
		return workListPointDetailDTO;
	}
	public void setWorkListPointDetailDTO(WorkListPointDetailDTO workListPointDetailDTO) {
		this.workListPointDetailDTO = workListPointDetailDTO;
	}
}
