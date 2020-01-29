package dream.work.rpt.energyuseprecon.year.form;

import common.struts.BaseForm;
import dream.work.rpt.energyuseprecon.year.dto.EnergyUsePreConYearCommonDTO;


/**
 * EnergyUsePreConYear Page - List Form
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @struts.form name="energyUsePreConYearListForm"
 * */
public class EnergyUsePreConYearListForm extends BaseForm {
    
    private EnergyUsePreConYearCommonDTO energyUsePreConYearCommonDTO = new EnergyUsePreConYearCommonDTO();
    
    public EnergyUsePreConYearCommonDTO getEnergyUsePreConYearCommonDTO() {
        return energyUsePreConYearCommonDTO;
    }

    public void setEnergyUsePreConYearCommonDTO(EnergyUsePreConYearCommonDTO energyUsePreConYearCommonDTO) {
        this.energyUsePreConYearCommonDTO = energyUsePreConYearCommonDTO;
    }
    
}