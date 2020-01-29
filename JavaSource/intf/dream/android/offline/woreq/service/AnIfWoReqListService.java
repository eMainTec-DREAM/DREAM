package intf.dream.android.offline.woreq.service;

import java.util.List;
import java.util.Map;

/**
 *  service
 * @author  
 * @version $Id:  $
 * @since   1.0
 */
public interface AnIfWoReqListService
{     
    public List findWoReqList(Map map) throws Exception;
    public List findWoReqFileList(Map map) throws Exception;
    public List findWoReqResList(Map map) throws Exception;

    public int saveWoReqList(List list) throws Exception;
    public int saveWoResList(List list) throws Exception;
}
