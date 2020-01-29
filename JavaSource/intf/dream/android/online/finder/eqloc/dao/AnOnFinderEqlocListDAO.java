package intf.dream.android.online.finder.eqloc.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface AnOnFinderEqlocListDAO extends  BaseJdbcDaoSupportIntf
{
    public List findEqlocList(Map map) throws Exception;
}