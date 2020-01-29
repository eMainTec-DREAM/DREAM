package intf.dream.cricket.finder.eqasmb.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface CricketFinderEqAsmbListDAO extends  BaseJdbcDaoSupportIntf
{
    public List findEqAsmbList(Map map) throws Exception;
}