package dream.mgr.rpt.cp.form;

import dream.comm.form.MaFinderAcForm;
import dream.mgr.rpt.cp.dto.LovMgrRptCpDTO;

/**
 * ��¹� ���� Form
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="lovMgrRptCpForm"
 */
public class LovMgrRptCpForm extends MaFinderAcForm
{
    /** ��¹� ���� DTO */
    private LovMgrRptCpDTO lovMgrRptCpDTO = new LovMgrRptCpDTO();

	public LovMgrRptCpDTO getLovMgrRptCpDTO() {
		return lovMgrRptCpDTO;
	}

	public void setLovMgrRptCpDTO(LovMgrRptCpDTO lovMgrRptCpDTO) {
		this.lovMgrRptCpDTO = lovMgrRptCpDTO;
	}
}
