package dream.part.adj.stkmove.form;

import common.struts.BaseForm;
import dream.part.adj.stkmove.dto.PartAdjStkMoveCommonDTO;

/**
 * ����̵� - ��� form
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="partAdjStkMoveListForm"
 */
public class PartAdjStkMoveListForm extends BaseForm
{    
    //===============================================================
    /** ����̵� ���� */
    private PartAdjStkMoveCommonDTO partAdjStkMoveCommonDTO = new PartAdjStkMoveCommonDTO();
    
	public PartAdjStkMoveCommonDTO getPartAdjStkMoveCommonDTO() {
		return partAdjStkMoveCommonDTO;
	}

	public void setPartAdjStkMoveCommonDTO(PartAdjStkMoveCommonDTO partAdjStkMoveCommonDTO) {
		this.partAdjStkMoveCommonDTO = partAdjStkMoveCommonDTO;
	}
}
