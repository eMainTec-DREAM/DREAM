package intf.dream.bee.finder.wh.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface BeeFinderWhListDAO extends  BaseJdbcDaoSupportIntf
{
    public List findWhList(Map map) throws Exception;
}