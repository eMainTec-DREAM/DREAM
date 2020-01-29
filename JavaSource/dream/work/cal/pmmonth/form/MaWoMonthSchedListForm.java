package dream.work.cal.pmmonth.form;

import common.struts.BaseForm;
import dream.work.cal.pmmonth.dto.MaWoMonthSchedCommonDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * ������������ - ��� form
 * @author  kim21017
 * @version $Id: MaWoMonthSchedListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maWoMonthSchedListForm"
 */
public class MaWoMonthSchedListForm extends BaseForm
{    
    //===============================================================
    /** ������������ ���� */
    private MaWoMonthSchedCommonDTO maWoMonthSchedCommonDTO = new MaWoMonthSchedCommonDTO();

    /** �۾� ���� */
    private MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();

    
	public MaWoMonthSchedCommonDTO getMaWoMonthSchedCommonDTO() {
		return maWoMonthSchedCommonDTO;
	}
	public void setMaWoMonthSchedCommonDTO(MaWoMonthSchedCommonDTO maWoMonthSchedCommonDTO) {
		this.maWoMonthSchedCommonDTO = maWoMonthSchedCommonDTO;
	}

	public MaWoResultMstrCommonDTO getMaWoResultMstrCommonDTO() {
		return maWoResultMstrCommonDTO;
	}
	public void setMaWoResultMstrCommonDTO(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) {
		this.maWoResultMstrCommonDTO = maWoResultMstrCommonDTO;
	}

}
