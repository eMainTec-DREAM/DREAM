package intf.dream.cricket.common.service;

import java.util.List;
import java.util.Map;

/**
 *  service
 * @author  
 * @version $Id:  $
 * @since   1.0
 */
public interface CricketCommonListService
{     
    public List findNextVal(Map map) throws Exception;
    public List findConfigVal(Map map) throws Exception;
}
