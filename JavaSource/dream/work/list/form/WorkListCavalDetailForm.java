package dream.work.list.form;

import common.struts.BaseForm;
import dream.work.list.dto.WorkListCavalDetailDTO;
import dream.work.list.dto.WorkListCavalListDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * 작업결과 작업자
 * @author  kim2107
 * @version $Id: WorkListCavalDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="workListCavalDetailForm"
 */
public class WorkListCavalDetailForm extends BaseForm
{    
    //===============================================================
    /** 공통 DTO */
    private MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();
	/** 작업결과 작업자 목록 DTO  */
    private WorkListCavalListDTO workListCavalListDTO = new WorkListCavalListDTO();
	/** 작업결과 작업자 상세 DTO  */
    private WorkListCavalDetailDTO workListCavalDetailDTO = new WorkListCavalDetailDTO();
    
	public WorkListCavalListDTO getWorkListCavalListDTO() {
		return workListCavalListDTO;
	}
	public void setWorkListCavalListDTO(WorkListCavalListDTO workListCavalListDTO) {
		this.workListCavalListDTO = workListCavalListDTO;
	}
	public WorkListCavalDetailDTO getWorkListCavalDetailDTO() {
		return workListCavalDetailDTO;
	}
	public void setWorkListCavalDetailDTO(WorkListCavalDetailDTO workListCavalDetailDTO) {
		this.workListCavalDetailDTO = workListCavalDetailDTO;
	}
	public MaWoResultMstrCommonDTO getMaWoResultMstrCommonDTO() {
		return maWoResultMstrCommonDTO;
	}
	public void setMaWoResultMstrCommonDTO(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) {
		this.maWoResultMstrCommonDTO = maWoResultMstrCommonDTO;
	}
}
