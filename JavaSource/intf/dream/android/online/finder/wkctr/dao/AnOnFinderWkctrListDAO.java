package intf.dream.android.online.finder.wkctr.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface AnOnFinderWkctrListDAO extends  BaseJdbcDaoSupportIntf
{
    public List findWkctrList(Map map) throws Exception;
}