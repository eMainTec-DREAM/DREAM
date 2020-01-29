package intf.dream.bee.stock.form;

import common.struts.BaseForm;
import intf.dream.bee.stock.dto.BeeStockCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="beeStockListForm"
 */
public class BeeStockListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private BeeStockCommonDTO beeStockCommonDTO = new BeeStockCommonDTO();

	public BeeStockCommonDTO getBeeStockCommonDTO() {
		return beeStockCommonDTO;
	}

	public void setBeeStockCommonDTO(BeeStockCommonDTO beeStockCommonDTO) {
		this.beeStockCommonDTO = beeStockCommonDTO;
	}

    
}
