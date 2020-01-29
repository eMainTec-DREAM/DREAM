package intf.dream.android.online.menu.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface AnOnMenuListDAO extends  BaseJdbcDaoSupportIntf
{
    public List findMenuList(Map map) throws Exception;
}