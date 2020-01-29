package intf.dream.ant.pmi.routine.service;

import java.util.List;
import java.util.Map;

/**
 *  service
 * @author  
 * @version $Id:  $
 * @since   1.0
 */
public interface AntPmiRoutineListService
{     
    public List findSchedList(Map map) throws Exception;
    public List findPmlstList(Map map) throws Exception;
    public List findPmequipList(Map map) throws Exception;
    public List findPmpointList(Map map) throws Exception;
    
    public int saveInsList(List list) throws Exception;
}