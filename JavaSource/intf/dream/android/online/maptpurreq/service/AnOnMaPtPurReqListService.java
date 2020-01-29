package intf.dream.android.online.maptpurreq.service;

import java.util.List;
import java.util.Map;

/**
 *  service
 * @author  
 * @version $Id:  $
 * @since   1.0
 */
public interface AnOnMaPtPurReqListService
{     
    public List findMaPtPurReqList(Map map) throws Exception;
    public int deleteMaPtPurReq(List list) throws Exception;
    public int insertMaPtPurReq(List list) throws Exception;
    public int updateMaPtPurReq(List list) throws Exception;
    public int confirmMaPtPurReq(List list) throws Exception;
}
