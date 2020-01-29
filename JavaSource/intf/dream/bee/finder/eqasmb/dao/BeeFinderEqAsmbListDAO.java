package intf.dream.bee.finder.eqasmb.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface BeeFinderEqAsmbListDAO extends  BaseJdbcDaoSupportIntf
{
    public List findEqAsmbList(Map map) throws Exception;
}