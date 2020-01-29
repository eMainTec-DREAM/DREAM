package dream.mgr.rpt.cp.form;

import dream.comm.form.MaFinderAcForm;
import dream.mgr.rpt.cp.dto.LovMgrRptCpDTO;

/**
 * 출력물 선택 Form
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="lovMgrRptCpForm"
 */
public class LovMgrRptCpForm extends MaFinderAcForm
{
    /** 출력물 선택 DTO */
    private LovMgrRptCpDTO lovMgrRptCpDTO = new LovMgrRptCpDTO();

	public LovMgrRptCpDTO getLovMgrRptCpDTO() {
		return lovMgrRptCpDTO;
	}

	public void setLovMgrRptCpDTO(LovMgrRptCpDTO lovMgrRptCpDTO) {
		this.lovMgrRptCpDTO = lovMgrRptCpDTO;
	}
}
