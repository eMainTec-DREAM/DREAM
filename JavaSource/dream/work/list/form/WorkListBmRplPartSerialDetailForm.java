package dream.work.list.form;

import common.struts.BaseForm;
import dream.work.list.dto.WorkListBmRplPartSerialDetailDTO;
import dream.work.list.dto.WorkListBmRplPartSerialListDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultPartDetailDTO;

/**
 * �۾���� ����Serial ��
 * @author  kim2107
 * @version $Id: WorkListBmRplPartSerialDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="workListBmRplPartSerialDetailForm"
 */
public class WorkListBmRplPartSerialDetailForm extends BaseForm
{    
    //===============================================================
    /** ���� DTO */
    private MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();
	/** �۾���� ��ǰ Serial ��� DTO  */
    private WorkListBmRplPartSerialListDTO workListBmRplPartSerialListDTO = new WorkListBmRplPartSerialListDTO();
	/** �۾���� ��ǰ Serial �� DTO  */
    private WorkListBmRplPartSerialDetailDTO workListBmRplPartSerialDetailDTO = new WorkListBmRplPartSerialDetailDTO();
    
    private MaWoResultPartDetailDTO maWoResultPartDetailDTO = new MaWoResultPartDetailDTO();
    
    
	public MaWoResultPartDetailDTO getMaWoResultPartDetailDTO() {
		return maWoResultPartDetailDTO;
	}
	public void setMaWoResultPartDetailDTO(MaWoResultPartDetailDTO maWoResultPartDetailDTO) {
		this.maWoResultPartDetailDTO = maWoResultPartDetailDTO;
	}
	public WorkListBmRplPartSerialListDTO getWorkListBmRplPartSerialListDTO() {
		return workListBmRplPartSerialListDTO;
	}
	public void setWorkListBmRplPartSerialListDTO(WorkListBmRplPartSerialListDTO workListBmRplPartSerialListDTO) {
		this.workListBmRplPartSerialListDTO = workListBmRplPartSerialListDTO;
	}
	public WorkListBmRplPartSerialDetailDTO getWorkListBmRplPartSerialDetailDTO() {
		return workListBmRplPartSerialDetailDTO;
	}
	public void setWorkListBmRplPartSerialDetailDTO(WorkListBmRplPartSerialDetailDTO workListBmRplPartSerialDetailDTO) {
		this.workListBmRplPartSerialDetailDTO = workListBmRplPartSerialDetailDTO;
	}
	public MaWoResultMstrCommonDTO getMaWoResultMstrCommonDTO() {
		return maWoResultMstrCommonDTO;
	}
	public void setMaWoResultMstrCommonDTO(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) {
		this.maWoResultMstrCommonDTO = maWoResultMstrCommonDTO;
	}
}
