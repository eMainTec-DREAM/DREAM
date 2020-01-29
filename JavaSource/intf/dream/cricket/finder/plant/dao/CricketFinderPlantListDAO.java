package intf.dream.cricket.finder.plant.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface CricketFinderPlantListDAO extends  BaseJdbcDaoSupportIntf
{
    public List findPlantList(Map map) throws Exception;
}