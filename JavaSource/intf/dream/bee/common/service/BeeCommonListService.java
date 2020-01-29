package intf.dream.bee.common.service;

import java.util.List;
import java.util.Map;

/**
 *  service
 * @author  
 * @version $Id:  $
 * @since   1.0
 */
public interface BeeCommonListService
{     
    public List findNextVal(Map map) throws Exception;
    public List findConfigVal(Map map) throws Exception;
    
    
    public void insertTraceLog(Map<String, Object> data) throws Exception;
}
