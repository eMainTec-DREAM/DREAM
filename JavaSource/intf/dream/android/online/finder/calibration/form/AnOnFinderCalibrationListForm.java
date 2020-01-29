package intf.dream.android.online.finder.calibration.form;

import common.struts.BaseForm;
import intf.dream.android.online.finder.calibration.dto.AnOnFinderCalibrationCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="anOnFinderCalibrationListForm"
 */
public class AnOnFinderCalibrationListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private AnOnFinderCalibrationCommonDTO anOnFinderCalibrationCommonDTO = new AnOnFinderCalibrationCommonDTO();

	public AnOnFinderCalibrationCommonDTO getAnOnFinderCalibrationCommonDTO() {
		return anOnFinderCalibrationCommonDTO;
	}

	public void setAnOnFinderCalibrationCommonDTO(AnOnFinderCalibrationCommonDTO anOnFinderCalibrationCommonDTO) {
		this.anOnFinderCalibrationCommonDTO = anOnFinderCalibrationCommonDTO;
	}

    
}
