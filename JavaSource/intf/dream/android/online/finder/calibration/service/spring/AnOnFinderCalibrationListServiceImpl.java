package intf.dream.android.online.finder.calibration.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.android.online.finder.calibration.dao.AnOnFinderCalibrationListDAO;
import intf.dream.android.online.finder.calibration.service.AnOnFinderCalibrationListService;

/**
 * serviceimpl
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="anOnFinderCalibrationListServiceTarget"
 * @spring.txbn id="anOnFinderCalibrationListService"
 * @spring.property name="anOnFinderCalibrationListDAO" ref="anOnFinderCalibrationListDAO"
 */
public class AnOnFinderCalibrationListServiceImpl implements AnOnFinderCalibrationListService
{
    private AnOnFinderCalibrationListDAO anOnFinderCalibrationListDAO = null;

	public AnOnFinderCalibrationListDAO getAnOnFinderCalibrationListDAO() {
		return anOnFinderCalibrationListDAO;
	}
	public void setAnOnFinderCalibrationListDAO(AnOnFinderCalibrationListDAO anOnFinderCalibrationListDAO) {
		this.anOnFinderCalibrationListDAO = anOnFinderCalibrationListDAO;
	}
	
	public List findCalibrationList(Map map) throws Exception
	{      
		return anOnFinderCalibrationListDAO.findCalibrationList(map);
	}
}

