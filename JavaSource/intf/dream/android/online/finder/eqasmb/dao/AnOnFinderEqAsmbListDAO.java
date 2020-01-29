package intf.dream.android.online.finder.eqasmb.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface AnOnFinderEqAsmbListDAO extends  BaseJdbcDaoSupportIntf
{
    public List findEqAsmbList(Map map) throws Exception;
}