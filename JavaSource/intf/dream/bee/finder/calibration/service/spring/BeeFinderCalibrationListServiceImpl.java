package intf.dream.bee.finder.calibration.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.bee.finder.calibration.dao.BeeFinderCalibrationListDAO;
import intf.dream.bee.finder.calibration.service.BeeFinderCalibrationListService;

/**
 * serviceimpl
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="beeFinderCalibrationListServiceTarget"
 * @spring.txbn id="beeFinderCalibrationListService"
 * @spring.property name="beeFinderCalibrationListDAO" ref="beeFinderCalibrationListDAO"
 */
public class BeeFinderCalibrationListServiceImpl implements BeeFinderCalibrationListService
{
    private BeeFinderCalibrationListDAO beeFinderCalibrationListDAO = null;

	public BeeFinderCalibrationListDAO getBeeFinderCalibrationListDAO() {
		return beeFinderCalibrationListDAO;
	}
	public void setBeeFinderCalibrationListDAO(BeeFinderCalibrationListDAO beeFinderCalibrationListDAO) {
		this.beeFinderCalibrationListDAO = beeFinderCalibrationListDAO;
	}
	
	public List findCalibrationList(Map map) throws Exception
	{      
		return beeFinderCalibrationListDAO.findCalibrationList(map);
	}
}

