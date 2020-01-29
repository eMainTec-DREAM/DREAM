package intf.dream.android.online.finder.calibration.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface AnOnFinderCalibrationListDAO extends  BaseJdbcDaoSupportIntf
{
    public List findCalibrationList(Map map) throws Exception;
}