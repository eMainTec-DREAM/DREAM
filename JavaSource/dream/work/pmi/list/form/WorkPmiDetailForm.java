package dream.work.pmi.list.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.pers.appreq.dto.AppReqCommonDTO;
import dream.work.list.dto.MaWoResultFailDetailDTO;
import dream.work.pmi.list.dto.WorkPmiCommonDTO;
import dream.work.pmi.list.dto.WorkPmiDetailDTO;


/**
 * �����۾�- �� Form
 * @author  kim21017
 * @version $Id: WorkPmiDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="workPmiDetailForm"
 */
public class WorkPmiDetailForm extends BaseForm
{
    //========================================================================
    /** �۾���� ���� */ 
    private WorkPmiCommonDTO workPmiCommonDTO = new WorkPmiCommonDTO();
    //========================================================================
    /** �۾���� �� */
    private WorkPmiDetailDTO workPmiDetailDTO = new WorkPmiDetailDTO();
    
    /** ÷�ι��� DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();
    /** �۾���� ���峻�� �� DTO  */
    private MaWoResultFailDetailDTO maWoResultFailDetailDTO = new MaWoResultFailDetailDTO();
    /** ���� ���� �̷� DTO  */
    private AppReqCommonDTO appReqCommonDTO = new AppReqCommonDTO();

	public MaWoResultFailDetailDTO getMaWoResultFailDetailDTO() {
		return maWoResultFailDetailDTO;
	}

	public AppReqCommonDTO getAppReqCommonDTO() {
		return appReqCommonDTO;
	}

	public void setAppReqCommonDTO(AppReqCommonDTO appReqCommonDTO) {
		this.appReqCommonDTO = appReqCommonDTO;
	}

	public void setMaWoResultFailDetailDTO(MaWoResultFailDetailDTO maWoResultFailDetailDTO) {
		this.maWoResultFailDetailDTO = maWoResultFailDetailDTO;
	}

	public MaDocLibCommonDTO getMaDocLibCommonDTO()
    {
        return maDocLibCommonDTO;
    }

    public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO)
    {
        this.maDocLibCommonDTO = maDocLibCommonDTO;
    }
    
	public WorkPmiCommonDTO getWorkPmiCommonDTO() {
		return workPmiCommonDTO;
	}

    public void setWorkPmiCommonDTO(WorkPmiCommonDTO workPmiCommonDTO) {
		this.workPmiCommonDTO = workPmiCommonDTO;
	}

	public WorkPmiDetailDTO getWorkPmiDetailDTO() {
		return workPmiDetailDTO;
	}

	public void setWorkPmiDetailDTO(WorkPmiDetailDTO workPmiDetailDTO) {
		this.workPmiDetailDTO = workPmiDetailDTO;
	}
}
