package dream.work.list.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.work.cal.pmptrlmonth.dto.WorkCalPmPtrlMonthCommonDTO;
import dream.work.list.dto.WorkListPtrlResultCommonDTO;
import dream.work.list.dto.WorkListPtrlResultMstrDetailDTO;

/**
 * 순회점검 작업결과
 * @author  youngjoo38
 * @version $Id: WorkListPtrlResultMstrDetailForm.java,v 1.0 2017/09/15 10:22:27 youngjoo38 Exp $
 * @since   1.0
 *
 * @struts.form name="workListPtrlResultMstrDetailForm"
 */
public class WorkListPtrlResultMstrDetailForm extends BaseForm
{    
    //===============================================================
    /** 공통 DTO */
    private WorkListPtrlResultCommonDTO workListPtrlResultCommonDTO = new WorkListPtrlResultCommonDTO();
	/** 순회점검 측정값 상세 DTO  */
    private WorkListPtrlResultMstrDetailDTO workListPtrlResultMstrDetailDTO = new WorkListPtrlResultMstrDetailDTO();
    
    private WorkCalPmPtrlMonthCommonDTO workCalPmPtrlMonthCommonDTO = new WorkCalPmPtrlMonthCommonDTO();
    
    /** 첨부문서 DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();
    
    
    public WorkCalPmPtrlMonthCommonDTO getWorkCalPmPtrlMonthCommonDTO()
    {
        return workCalPmPtrlMonthCommonDTO;
    }
    public void setWorkCalPmPtrlMonthCommonDTO(
            WorkCalPmPtrlMonthCommonDTO workCalPmPtrlMonthCommonDTO)
    {
        this.workCalPmPtrlMonthCommonDTO = workCalPmPtrlMonthCommonDTO;
    }
    public MaDocLibCommonDTO getMaDocLibCommonDTO()
    {
        return maDocLibCommonDTO;
    }
    public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO)
    {
        this.maDocLibCommonDTO = maDocLibCommonDTO;
    }
    public WorkListPtrlResultCommonDTO getWorkListPtrlResultCommonDTO()
    {
        return workListPtrlResultCommonDTO;
    }
    public void setWorkListPtrlResultCommonDTO(
            WorkListPtrlResultCommonDTO workListPtrlResultCommonDTO)
    {
        this.workListPtrlResultCommonDTO = workListPtrlResultCommonDTO;
    }
    public WorkListPtrlResultMstrDetailDTO getWorkListPtrlResultMstrDetailDTO()
    {
        return workListPtrlResultMstrDetailDTO;
    }
    public void setWorkListPtrlResultMstrDetailDTO(
            WorkListPtrlResultMstrDetailDTO workListPtrlResultMstrDetailDTO)
    {
        this.workListPtrlResultMstrDetailDTO = workListPtrlResultMstrDetailDTO;
    }
}
