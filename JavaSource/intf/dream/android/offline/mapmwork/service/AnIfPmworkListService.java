package intf.dream.android.offline.mapmwork.service;

import java.util.List;
import java.util.Map;

/**
 *  service
 * @author  
 * @version $Id:  $
 * @since   1.0
 */
public interface AnIfPmworkListService
{     
    public List findHdrList(Map map) throws Exception;
    public List findWocraftList(Map map) throws Exception;
    public List findWopartsList(Map map) throws Exception;
    public List findWoequipList(Map map) throws Exception;

    public int saveWorkorder(List list) throws Exception;
}
