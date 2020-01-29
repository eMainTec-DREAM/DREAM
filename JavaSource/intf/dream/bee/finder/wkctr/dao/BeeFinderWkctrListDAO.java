package intf.dream.bee.finder.wkctr.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface BeeFinderWkctrListDAO extends  BaseJdbcDaoSupportIntf
{
    public List findWkctrList(Map map) throws Exception;
}