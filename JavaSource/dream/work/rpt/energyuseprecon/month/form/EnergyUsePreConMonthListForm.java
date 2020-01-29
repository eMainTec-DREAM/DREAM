package dream.work.rpt.energyuseprecon.month.form;

import common.struts.BaseForm;
import dream.work.rpt.energyuseprecon.month.dto.EnergyUsePreConMonthCommonDTO;
import dream.work.rpt.energyuseprecon.month.dto.EnergyUsePreConMonthDetailDTO;


/**
 * EnergyUsePreConMonth Page - List Form
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @struts.form name="energyUsePreConMonthListForm"
 * */
public class EnergyUsePreConMonthListForm extends BaseForm {
    
    private EnergyUsePreConMonthCommonDTO energyUsePreConMonthCommonDTO = new EnergyUsePreConMonthCommonDTO();
    private EnergyUsePreConMonthDetailDTO energyUsePreConMonthDetailDTO = new EnergyUsePreConMonthDetailDTO();

    public EnergyUsePreConMonthCommonDTO getEnergyUsePreConMonthCommonDTO() {
        return energyUsePreConMonthCommonDTO;
    }

    public void setEnergyUsePreConMonthCommonDTO(EnergyUsePreConMonthCommonDTO energyUsePreConMonthCommonDTO) {
        this.energyUsePreConMonthCommonDTO = energyUsePreConMonthCommonDTO;
    }

    public EnergyUsePreConMonthDetailDTO getEnergyUsePreConMonthDetailDTO()
    {
        return energyUsePreConMonthDetailDTO;
    }

    public void setEnergyUsePreConMonthDetailDTO(
            EnergyUsePreConMonthDetailDTO energyUsePreConMonthDetailDTO)
    {
        this.energyUsePreConMonthDetailDTO = energyUsePreConMonthDetailDTO;
    }
    
}