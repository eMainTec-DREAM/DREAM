package dream.part.rpt.mapartrptmonthly.form;

import common.struts.BaseForm;
import dream.part.rpt.mapartrptmonthly.dto.PartRptMonthlyStockListDTO;

/**
 * 부품수불부 요약 FORM
 * @author  euna0207
 * @version $Id: PartRptMonthlyStockListForm.java,v 1.0 2015/12/01 09:13:09 euna0207 Exp $
 * @since   1.0
 *
 * @struts.form name="partRptMonthlyStockListForm"
 */
public class PartRptMonthlyStockListForm extends BaseForm
{    
    private PartRptMonthlyStockListDTO partRptMonthlyStockListDTO = new PartRptMonthlyStockListDTO();

	public PartRptMonthlyStockListDTO getPartRptMonthlyStockListDTO() {
		return partRptMonthlyStockListDTO;
	}

	public void setPartRptMonthlyStockListDTO(PartRptMonthlyStockListDTO partRptMonthlyStockListDTO) {
		this.partRptMonthlyStockListDTO = partRptMonthlyStockListDTO;
	}

}
