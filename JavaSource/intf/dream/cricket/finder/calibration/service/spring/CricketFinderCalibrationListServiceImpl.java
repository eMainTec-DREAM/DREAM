package intf.dream.cricket.finder.calibration.service.spring;

import java.util.List;
import java.util.Map;

import intf.dream.cricket.finder.calibration.dao.CricketFinderCalibrationListDAO;
import intf.dream.cricket.finder.calibration.service.CricketFinderCalibrationListService;

/**
 * serviceimpl
 * @author kim21017
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="cricketFinderCalibrationListServiceTarget"
 * @spring.txbn id="cricketFinderCalibrationListService"
 * @spring.property name="cricketFinderCalibrationListDAO" ref="cricketFinderCalibrationListDAO"
 */
public class CricketFinderCalibrationListServiceImpl implements CricketFinderCalibrationListService
{
    private CricketFinderCalibrationListDAO cricketFinderCalibrationListDAO = null;

	public CricketFinderCalibrationListDAO getCricketFinderCalibrationListDAO() {
		return cricketFinderCalibrationListDAO;
	}
	public void setCricketFinderCalibrationListDAO(CricketFinderCalibrationListDAO cricketFinderCalibrationListDAO) {
		this.cricketFinderCalibrationListDAO = cricketFinderCalibrationListDAO;
	}
	
	public List findCalibrationList(Map map) throws Exception
	{      
		return cricketFinderCalibrationListDAO.findCalibrationList(map);
	}
}

