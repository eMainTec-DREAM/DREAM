package dream.edu.rpt.emplist.form;

import common.struts.BaseForm;

import dream.edu.rpt.emplist.dto.EduRptEmpCommonDTO;

/**
 * 자격증분류 - 목록 form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="eduRptEmpListForm"
 */
public class EduRptEmpListForm extends BaseForm
{
    //===============================================================
    /** 자격증분류 공통 */
    private EduRptEmpCommonDTO eduRptEmpCommonDTO = new EduRptEmpCommonDTO();

	public EduRptEmpCommonDTO getEduRptEmpCommonDTO() {
		return eduRptEmpCommonDTO;
	}

	public void setEduRptEmpCommonDTO(EduRptEmpCommonDTO eduRptEmpCommonDTO) {
		this.eduRptEmpCommonDTO = eduRptEmpCommonDTO;
	}

	



}
