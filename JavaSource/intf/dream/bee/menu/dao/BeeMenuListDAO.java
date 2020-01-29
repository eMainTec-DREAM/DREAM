package intf.dream.bee.menu.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface BeeMenuListDAO extends  BaseJdbcDaoSupportIntf
{
    public List findMenuList(Map map) throws Exception;
}