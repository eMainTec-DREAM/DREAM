package dream.part.rpt.stockkpi.form;

import common.struts.BaseForm;
import dream.part.rpt.stockkpi.dto.StockKpiCommonDTO;

/**
 * �����ǥ
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="stockKpiListForm"
 */
public class StockKpiListForm extends BaseForm
{    
    //===============================================================
    /** ���� */
    private StockKpiCommonDTO stockKpiCommonDTO = new StockKpiCommonDTO();
    
    public StockKpiCommonDTO getStockKpiCommonDTO()
    {
        return stockKpiCommonDTO;
    }

    public void setStockKpiCommonDTO(
            StockKpiCommonDTO stockKpiCommonDTO)
    {
        this.stockKpiCommonDTO = stockKpiCommonDTO;
    }
    
}
