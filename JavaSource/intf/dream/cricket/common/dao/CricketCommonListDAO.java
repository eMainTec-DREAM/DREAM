package intf.dream.cricket.common.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface CricketCommonListDAO extends  BaseJdbcDaoSupportIntf
{
    public List findNextVal(Map map) throws Exception;
    public List findConfigVal(Map map) throws Exception;
}