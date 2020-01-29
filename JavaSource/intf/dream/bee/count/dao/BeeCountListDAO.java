package intf.dream.bee.count.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface BeeCountListDAO extends  BaseJdbcDaoSupportIntf
{
    public List findMainList(Map map) throws Exception;
}