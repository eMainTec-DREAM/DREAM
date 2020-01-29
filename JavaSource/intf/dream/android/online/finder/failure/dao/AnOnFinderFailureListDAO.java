package intf.dream.android.online.finder.failure.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface AnOnFinderFailureListDAO extends  BaseJdbcDaoSupportIntf
{
    public List findFailureList(Map map) throws Exception;
}