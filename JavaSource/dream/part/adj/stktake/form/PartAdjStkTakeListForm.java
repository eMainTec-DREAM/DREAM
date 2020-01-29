package dream.part.adj.stktake.form;

import common.struts.BaseForm;
import dream.part.adj.stktake.dto.PartAdjStkTakeCommonDTO;

/**
 * 부품실사 - 목록 form
 * @author  kim21017
 * @version $Id: PartAdjStkTakeListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="partAdjStkTakeListForm"
 */
public class PartAdjStkTakeListForm extends BaseForm
{    
    //===============================================================
    /** 구매신청 공통 */
    private PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO = new PartAdjStkTakeCommonDTO();
    
	public PartAdjStkTakeCommonDTO getPartAdjStkTakeCommonDTO() {
		return partAdjStkTakeCommonDTO;
	}

	public void setPartAdjStkTakeCommonDTO(PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO) {
		this.partAdjStkTakeCommonDTO = partAdjStkTakeCommonDTO;
	}
}
