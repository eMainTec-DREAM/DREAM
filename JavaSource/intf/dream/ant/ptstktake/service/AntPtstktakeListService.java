package intf.dream.ant.ptstktake.service;

import java.util.List;
import java.util.Map;

/**
 *  service
 * @author  
 * @version $Id:  $
 * @since   1.0
 */
public interface AntPtstktakeListService
{     
    public List findStktakeList(Map map) throws Exception;
    public List findStktakeItem(Map map) throws Exception;
    public int savePtstktakeItem(List list) throws Exception;
}
