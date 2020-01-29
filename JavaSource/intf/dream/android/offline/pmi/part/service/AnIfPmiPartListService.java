package intf.dream.android.offline.pmi.part.service;

import java.util.List;
import java.util.Map;

/**
 *  service
 * @author  
 * @version $Id:  $
 * @since   1.0
 */
public interface AnIfPmiPartListService
{     
    public List findSchedList(Map map) throws Exception;
    public List findPmlstList(Map map) throws Exception;
    public List findPmequipList(Map map) throws Exception;
    public List findPmpointList(Map map) throws Exception;
    
    public int saveInsdList(List list) throws Exception;
}