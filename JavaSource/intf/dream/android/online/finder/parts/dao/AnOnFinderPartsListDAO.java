package intf.dream.android.online.finder.parts.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface AnOnFinderPartsListDAO extends  BaseJdbcDaoSupportIntf
{
    public List findPartsList(Map map) throws Exception;
}