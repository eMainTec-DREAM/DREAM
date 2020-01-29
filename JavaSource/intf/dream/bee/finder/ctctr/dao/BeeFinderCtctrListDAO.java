package intf.dream.bee.finder.ctctr.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface BeeFinderCtctrListDAO extends  BaseJdbcDaoSupportIntf
{
    public List findCtctrList(Map map) throws Exception;
}