package dream.work.list.energy.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.work.list.energy.dto.WorkListEnergyMstrListCommonDTO;
import dream.work.list.energy.dto.WorkListEnergyPointDetailDTO;
import dream.work.list.energy.dto.WorkListEnergyPointListDTO;

/**
 * 에너지 값 측정항목
 * @author  sy.yang
 * @version $Id: WorkListEnergyPointDetailForm.java,v 1.0 2015/12/04 09:09:54 sy.yang Exp $
 * @since   1.0
 *
 * @struts.form name="workListEnergyPointDetailForm"
 */
public class WorkListEnergyPointDetailForm extends BaseForm
{    
    //===============================================================
	/** 공통 */
    private WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO = new WorkListEnergyMstrListCommonDTO();
    /** 에너지 값 측정항목 상세 목록 DTO  */
    private WorkListEnergyPointListDTO workListEnergyPointListDTO = new WorkListEnergyPointListDTO();
	/** 에너지 값 측정항목 상세 DTO  */
    private WorkListEnergyPointDetailDTO workListEnergyPointDetailDTO = new WorkListEnergyPointDetailDTO();
    /** 첨부문서 DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();
    
    
	public WorkListEnergyMstrListCommonDTO getWorkListEnergyMstrListCommonDTO() {
		return workListEnergyMstrListCommonDTO;
	}
	public void setWorkListEnergyMstrListCommonDTO(WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO) {
		this.workListEnergyMstrListCommonDTO = workListEnergyMstrListCommonDTO;
	}
	public WorkListEnergyPointListDTO getWorkListEnergyPointListDTO() {
		return workListEnergyPointListDTO;
	}
	public void setWorkListEnergyPointListDTO(WorkListEnergyPointListDTO workListEnergyPointListDTO) {
		this.workListEnergyPointListDTO = workListEnergyPointListDTO;
	}
	public WorkListEnergyPointDetailDTO getWorkListEnergyPointDetailDTO() {
		return workListEnergyPointDetailDTO;
	}
	public void setWorkListEnergyPointDetailDTO(WorkListEnergyPointDetailDTO workListEnergyPointDetailDTO) {
		this.workListEnergyPointDetailDTO = workListEnergyPointDetailDTO;
	}
	public MaDocLibCommonDTO getMaDocLibCommonDTO() {
		return maDocLibCommonDTO;
	}
	public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO) {
		this.maDocLibCommonDTO = maDocLibCommonDTO;
	}
    
}
