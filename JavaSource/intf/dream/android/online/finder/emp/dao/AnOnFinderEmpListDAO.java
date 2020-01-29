package intf.dream.android.online.finder.emp.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface AnOnFinderEmpListDAO extends  BaseJdbcDaoSupportIntf
{
    public List findEmpList(Map map) throws Exception;
}