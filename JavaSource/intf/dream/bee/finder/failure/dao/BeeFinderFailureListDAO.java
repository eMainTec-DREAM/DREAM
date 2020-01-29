package intf.dream.bee.finder.failure.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface BeeFinderFailureListDAO extends  BaseJdbcDaoSupportIntf
{
    public List findFailureList(Map map) throws Exception;
}