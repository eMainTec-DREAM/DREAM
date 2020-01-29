package dream.work.list.form;

import common.struts.BaseForm;
import dream.work.list.dto.WorkListGnlCaEqDetailDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * 작업결과 표준기
 * @author  kim2107
 * @version $Id: WorkListGnlCaEqDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="workListGnlCaEqDetailForm"
 */
public class WorkListGnlCaEqDetailForm extends BaseForm
{    
    //===============================================================
    /** 공통 DTO */
    private MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();
	/** 작업결과 작업자 상세 DTO  */
    private WorkListGnlCaEqDetailDTO workListGnlCaEqDetailDTO = new WorkListGnlCaEqDetailDTO();
    
	public WorkListGnlCaEqDetailDTO getWorkListGnlCaEqDetailDTO() {
		return workListGnlCaEqDetailDTO;
	}
	public void setWorkListGnlCaEqDetailDTO(WorkListGnlCaEqDetailDTO workListGnlCaEqDetailDTO) {
		this.workListGnlCaEqDetailDTO = workListGnlCaEqDetailDTO;
	}
	public MaWoResultMstrCommonDTO getMaWoResultMstrCommonDTO() {
		return maWoResultMstrCommonDTO;
	}
	public void setMaWoResultMstrCommonDTO(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) {
		this.maWoResultMstrCommonDTO = maWoResultMstrCommonDTO;
	}
}
