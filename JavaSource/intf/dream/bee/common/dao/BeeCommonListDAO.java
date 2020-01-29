package intf.dream.bee.common.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface BeeCommonListDAO extends  BaseJdbcDaoSupportIntf
{
    public List findNextVal(Map map) throws Exception;
    public List findConfigVal(Map map) throws Exception;
    
    public void insertTraceLogHeader(Map data) throws Exception;
    public void insertTraceLogDetail(String traceLogId, List<Map<String,String>> results) throws Exception;
}