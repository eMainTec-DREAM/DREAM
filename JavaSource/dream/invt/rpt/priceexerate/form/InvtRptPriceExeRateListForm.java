package dream.invt.rpt.priceexerate.form;

import common.struts.BaseForm;
import dream.invt.rpt.priceexerate.dto.InvtRptPriceExeRateCommonDTO;


/**
 * ���ں� ������Ȳ ��� - List Form
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 * @struts.form name="invtRptPriceExeRateListForm"
 * */
public class InvtRptPriceExeRateListForm extends BaseForm {
    
    private InvtRptPriceExeRateCommonDTO invtRptPriceExeRateCommonDTO = new InvtRptPriceExeRateCommonDTO();

    public InvtRptPriceExeRateCommonDTO getInvtRptPriceExeRateCommonDTO() {
        return invtRptPriceExeRateCommonDTO;
    }

    public void setInvtRptPriceExeRateCommonDTO(InvtRptPriceExeRateCommonDTO invtRptPriceExeRateCommonDTO) {
        this.invtRptPriceExeRateCommonDTO = invtRptPriceExeRateCommonDTO;
    }

}