package dream.work.list.form;

import common.struts.BaseForm;
import dream.work.list.dto.WorkListBmRplPartSerialListDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultPartDetailDTO;

/**
 * �۾���� ��ǰ Serial ���
 * @author  kim21017
 * @version $Id: WorkListBmRplPartSerialListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="workListBmRplPartSerialListForm"
 */
public class WorkListBmRplPartSerialListForm extends BaseForm
{    
    //===============================================================
    /** ���� */
    private MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();
    /**  �۾���� ��ǰSerial ���  */
    private WorkListBmRplPartSerialListDTO workListBmRplPartSerialListDTO = new WorkListBmRplPartSerialListDTO();
	
    private MaWoResultPartDetailDTO maWoResultPartDetailDTO = new MaWoResultPartDetailDTO();

	public MaWoResultPartDetailDTO getMaWoResultPartDetailDTO() {
		return maWoResultPartDetailDTO;
	}

	public void setMaWoResultPartDetailDTO(MaWoResultPartDetailDTO maWoResultPartDetailDTO) {
		this.maWoResultPartDetailDTO = maWoResultPartDetailDTO;
	}

	public MaWoResultMstrCommonDTO getMaWoResultMstrCommonDTO() {
		return maWoResultMstrCommonDTO;
	}

	public void setMaWoResultMstrCommonDTO(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) {
		this.maWoResultMstrCommonDTO = maWoResultMstrCommonDTO;
	}

	public WorkListBmRplPartSerialListDTO getWorkListBmRplPartSerialListDTO() {
		return workListBmRplPartSerialListDTO;
	}

	public void setWorkListBmRplPartSerialListDTO(WorkListBmRplPartSerialListDTO workListBmRplPartSerialListDTO) {
		this.workListBmRplPartSerialListDTO = workListBmRplPartSerialListDTO;
	}
}
