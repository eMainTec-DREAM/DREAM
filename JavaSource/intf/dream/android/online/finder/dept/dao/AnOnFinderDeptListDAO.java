package intf.dream.android.online.finder.dept.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface AnOnFinderDeptListDAO extends  BaseJdbcDaoSupportIntf
{
    public List findDeptList(Map map) throws Exception;
}