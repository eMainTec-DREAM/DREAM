package dream.part.adj.stktake.form;

import common.struts.BaseForm;
import dream.part.adj.stktake.dto.PartAdjStkTakeCommonDTO;
import dream.part.adj.stktake.dto.PartAdjStkTakeItemDetailDTO;
import dream.part.adj.stktake.dto.PartAdjStkTakeListDTO;

/**
 * 부품실사item- 목록
 * @author  kim21017
 * @version $Id: PartAdjStkTakeListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="partAdjStkTakeItemListForm"
 */
public class PartAdjStkTakeItemListForm extends BaseForm
{    
    //===============================================================
    /** 구매신청 공통 */
    private PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO = new PartAdjStkTakeCommonDTO();
    /** 구매신청item  */
    private PartAdjStkTakeListDTO partAdjStkTakeListDTO = new PartAdjStkTakeListDTO();
    /** 구매신청item  Detail DTO  */
    private PartAdjStkTakeItemDetailDTO partAdjStkTakeItemDetailDTO = new PartAdjStkTakeItemDetailDTO();
    
	public PartAdjStkTakeItemDetailDTO getPartAdjStkTakeItemDetailDTO()
    {
        return partAdjStkTakeItemDetailDTO;
    }

    public void setPartAdjStkTakeItemDetailDTO(
            PartAdjStkTakeItemDetailDTO partAdjStkTakeItemDetailDTO)
    {
        this.partAdjStkTakeItemDetailDTO = partAdjStkTakeItemDetailDTO;
    }

    public PartAdjStkTakeCommonDTO getPartAdjStkTakeCommonDTO() {
		return partAdjStkTakeCommonDTO;
	}

	public void setPartAdjStkTakeCommonDTO(PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO) {
		this.partAdjStkTakeCommonDTO = partAdjStkTakeCommonDTO;
	}

	public PartAdjStkTakeListDTO getPartAdjStkTakeListDTO() {
		return partAdjStkTakeListDTO;
	}

	public void setPartAdjStkTakeListDTO(PartAdjStkTakeListDTO partAdjStkTakeListDTO) {
		this.partAdjStkTakeListDTO = partAdjStkTakeListDTO;
	}
}
