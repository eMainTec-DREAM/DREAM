package intf.dream.android.online.stock.form;

import common.struts.BaseForm;
import intf.dream.android.online.stock.dto.AnOnStockCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="anOnStockListForm"
 */
public class AnOnStockListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AnOnStockCommonDTO anOnStockCommonDTO = new AnOnStockCommonDTO();

	public AnOnStockCommonDTO getAnOnStockCommonDTO() {
		return anOnStockCommonDTO;
	}

	public void setAnOnStockCommonDTO(AnOnStockCommonDTO anOnStockCommonDTO) {
		this.anOnStockCommonDTO = anOnStockCommonDTO;
	}

    
}
