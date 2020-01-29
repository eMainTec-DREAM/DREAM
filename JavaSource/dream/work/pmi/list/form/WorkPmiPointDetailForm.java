package dream.work.pmi.list.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.work.pmi.list.dto.WorkPmiCommonDTO;
import dream.work.pmi.list.dto.WorkPmiPointDetailDTO;
import dream.work.pmi.list.dto.WorkPmiPointListDTO;

/**
 * 점검작업 점검
 * @author  kim2107
 * @version $Id: WorkPmiPointDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="workPmiPointDetailForm"
 */
public class WorkPmiPointDetailForm extends BaseForm
{    
    //===============================================================
    /** 공통 DTO */
    private WorkPmiCommonDTO workPmiCommonDTO = new WorkPmiCommonDTO();
	/** 작업결과 검사항목 목록 DTO  */
    private WorkPmiPointListDTO workPmiPointListDTO = new WorkPmiPointListDTO();
	/** 작업결과 검사항목 상세 DTO  */
    private WorkPmiPointDetailDTO workPmiPointDetailDTO = new WorkPmiPointDetailDTO();
    /** 첨부문서 DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();
    
    
	public MaDocLibCommonDTO getMaDocLibCommonDTO() {
		return maDocLibCommonDTO;
	}
	public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO) {
		this.maDocLibCommonDTO = maDocLibCommonDTO;
	}
	public WorkPmiPointListDTO getWorkPmiPointListDTO() {
		return workPmiPointListDTO;
	}
	public void setWorkPmiPointListDTO(WorkPmiPointListDTO workPmiPointListDTO) {
		this.workPmiPointListDTO = workPmiPointListDTO;
	}
	public WorkPmiPointDetailDTO getWorkPmiPointDetailDTO() {
		return workPmiPointDetailDTO;
	}
	public void setWorkPmiPointDetailDTO(WorkPmiPointDetailDTO workPmiPointDetailDTO) {
		this.workPmiPointDetailDTO = workPmiPointDetailDTO;
	}
	public WorkPmiCommonDTO getWorkPmiCommonDTO() {
		return workPmiCommonDTO;
	}
	public void setWorkPmiCommonDTO(WorkPmiCommonDTO workPmiCommonDTO) {
		this.workPmiCommonDTO = workPmiCommonDTO;
	}
}
