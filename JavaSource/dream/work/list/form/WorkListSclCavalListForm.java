package dream.work.list.form;

import common.struts.BaseForm;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.WorkListSclCavalListDTO;

/**
 * 작업결과 측정값(저울)
 * @author  kim2107
 * @version $Id: WorkListSclCavalListForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="workListSclCavalListForm"
 */
public class WorkListSclCavalListForm extends BaseForm
{    
    //===============================================================
    /** 공통 DTO */
    private MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();
	/** 작업결과 측정값(저울) DTO  */
    private WorkListSclCavalListDTO workListSclCavalListDTO = new WorkListSclCavalListDTO();
    
	public WorkListSclCavalListDTO getWorkListSclCavalListDTO() {
		return workListSclCavalListDTO;
	}
	public void setWorkListSclCavalListDTO(WorkListSclCavalListDTO workListSclCavalListDTO) {
		this.workListSclCavalListDTO = workListSclCavalListDTO;
	}
	public MaWoResultMstrCommonDTO getMaWoResultMstrCommonDTO() {
		return maWoResultMstrCommonDTO;
	}
	public void setMaWoResultMstrCommonDTO(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) {
		this.maWoResultMstrCommonDTO = maWoResultMstrCommonDTO;
	}
}
