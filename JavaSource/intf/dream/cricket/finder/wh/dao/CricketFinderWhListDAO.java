package intf.dream.cricket.finder.wh.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface CricketFinderWhListDAO extends  BaseJdbcDaoSupportIntf
{
    public List findWhList(Map map) throws Exception;
}