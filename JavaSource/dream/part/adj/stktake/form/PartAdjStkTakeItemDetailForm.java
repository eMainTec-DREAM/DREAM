package dream.part.adj.stktake.form;

import common.struts.BaseForm;
import dream.part.adj.stktake.dto.PartAdjStkTakeItemDetailDTO;
import dream.part.adj.stktake.dto.PartAdjStkTakeCommonDTO;
import dream.part.adj.stktake.dto.PartAdjStkTakeListDTO;

/**
 * 부품실사item
 * @author  kim2107
 * @version $Id: PartAdjStkTakeDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="partAdjStkTakeItemDetailForm"
 */
public class PartAdjStkTakeItemDetailForm extends BaseForm
{    
    //===============================================================
    /** 구매신청 - 공통 DTO */
    private PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO = new PartAdjStkTakeCommonDTO();
	/** 구매신청item  DTO  */
    private PartAdjStkTakeListDTO partAdjStkTakeListDTO = new PartAdjStkTakeListDTO();
	/** 구매신청item  Detail DTO  */
    private PartAdjStkTakeItemDetailDTO partAdjStkTakeItemDetailDTO = new PartAdjStkTakeItemDetailDTO();
    
	public PartAdjStkTakeListDTO getPartAdjStkTakeListDTO() {
		return partAdjStkTakeListDTO;
	}
	public void setPartAdjStkTakeListDTO(PartAdjStkTakeListDTO partAdjStkTakeListDTO) {
		this.partAdjStkTakeListDTO = partAdjStkTakeListDTO;
	}
	public PartAdjStkTakeItemDetailDTO getPartAdjStkTakeItemDetailDTO() {
		return partAdjStkTakeItemDetailDTO;
	}
	public void setPartAdjStkTakeItemDetailDTO(PartAdjStkTakeItemDetailDTO partAdjStkTakeItemDetailDTO) {
		this.partAdjStkTakeItemDetailDTO = partAdjStkTakeItemDetailDTO;
	}
	public PartAdjStkTakeCommonDTO getPartAdjStkTakeCommonDTO() {
		return partAdjStkTakeCommonDTO;
	}
	public void setPartAdjStkTakeCommonDTO(PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO) {
		this.partAdjStkTakeCommonDTO = partAdjStkTakeCommonDTO;
	}
	
}