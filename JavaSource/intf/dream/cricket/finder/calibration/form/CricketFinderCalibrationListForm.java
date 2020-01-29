package intf.dream.cricket.finder.calibration.form;

import common.struts.BaseForm;
import intf.dream.cricket.finder.calibration.dto.CricketFinderCalibrationCommonDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="cricketFinderCalibrationListForm"
 */
public class CricketFinderCalibrationListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë */
    private CricketFinderCalibrationCommonDTO cricketFinderCalibrationCommonDTO = new CricketFinderCalibrationCommonDTO();

	public CricketFinderCalibrationCommonDTO getCricketFinderCalibrationCommonDTO() {
		return cricketFinderCalibrationCommonDTO;
	}

	public void setCricketFinderCalibrationCommonDTO(CricketFinderCalibrationCommonDTO cricketFinderCalibrationCommonDTO) {
		this.cricketFinderCalibrationCommonDTO = cricketFinderCalibrationCommonDTO;
	}

    
}
