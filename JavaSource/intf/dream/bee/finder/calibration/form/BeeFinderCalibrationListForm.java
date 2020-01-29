package intf.dream.bee.finder.calibration.form;

import common.struts.BaseForm;
import intf.dream.bee.finder.calibration.dto.BeeFinderCalibrationCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="beeFinderCalibrationListForm"
 */
public class BeeFinderCalibrationListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private BeeFinderCalibrationCommonDTO beeFinderCalibrationCommonDTO = new BeeFinderCalibrationCommonDTO();

	public BeeFinderCalibrationCommonDTO getBeeFinderCalibrationCommonDTO() {
		return beeFinderCalibrationCommonDTO;
	}

	public void setBeeFinderCalibrationCommonDTO(BeeFinderCalibrationCommonDTO beeFinderCalibrationCommonDTO) {
		this.beeFinderCalibrationCommonDTO = beeFinderCalibrationCommonDTO;
	}

    
}
