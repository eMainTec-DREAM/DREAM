package dream.part.adj.stkmove.form;

import common.struts.BaseForm;
import dream.part.adj.stkmove.dto.PartAdjStkMoveCommonDTO;
import dream.part.adj.stkmove.dto.PartAdjStkMoveDetailDTO;

/**
 * 재고이동- 상세 Form
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="partAdjStkMoveDetailForm"
 */
public class PartAdjStkMoveDetailForm extends BaseForm
{
    //========================================================================
    /** 재고이동 공통 */ 
    private PartAdjStkMoveCommonDTO partAdjStkMoveCommonDTO = new PartAdjStkMoveCommonDTO();
    //========================================================================
    /** 재고이동 상세 */
    private PartAdjStkMoveDetailDTO partAdjStkMoveDetailDTO = new PartAdjStkMoveDetailDTO();
    
	public PartAdjStkMoveCommonDTO getPartAdjStkMoveCommonDTO() {
		return partAdjStkMoveCommonDTO;
	}

	public void setPartAdjStkMoveCommonDTO(PartAdjStkMoveCommonDTO partAdjStkMoveCommonDTO) {
		this.partAdjStkMoveCommonDTO = partAdjStkMoveCommonDTO;
	}

	public PartAdjStkMoveDetailDTO getPartAdjStkMoveDetailDTO() {
		return partAdjStkMoveDetailDTO;
	}

	public void setPartAdjStkMoveDetailDTO(PartAdjStkMoveDetailDTO partAdjStkMoveDetailDTO) {
		this.partAdjStkMoveDetailDTO = partAdjStkMoveDetailDTO;
	}
}