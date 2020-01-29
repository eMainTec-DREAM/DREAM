package intf.dream.bee.finder.eqctg.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface BeeFinderEqctgListDAO extends  BaseJdbcDaoSupportIntf
{
    public List findEqctgList(Map map) throws Exception;
}