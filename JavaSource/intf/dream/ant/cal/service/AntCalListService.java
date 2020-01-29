package intf.dream.ant.cal.service;

import java.util.List;
import java.util.Map;

/**
 *  service
 * @author  
 * @version $Id:  $
 * @since   1.0
 */
public interface AntCalListService
{     
    public List findHdrList(Map map) throws Exception;
    public List findWocraftList(Map map) throws Exception;
    public List findWocalibList(Map map) throws Exception;
    public List findWocalibValueList(Map map) throws Exception;
    public List findWoequipList(Map map) throws Exception;
    public List findWocalibStdEq(Map map) throws Exception;
    public List findWocalibGnlValue(Map map) throws Exception;
    public List findWocalibSclValue(Map map) throws Exception;

    public int saveWorkorder(List list) throws Exception;
}
