package intf.dream.android.online.user.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface AnOnUserListDAO extends  BaseJdbcDaoSupportIntf
{
    public List findList(Map map) throws Exception;
    public int updateFilter(Map map, String interval) throws Exception;
}