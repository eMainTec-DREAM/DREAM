package intf.dream.cricket.count.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface CricketCountListDAO extends  BaseJdbcDaoSupportIntf
{
    public List findMainList(Map map) throws Exception;
}