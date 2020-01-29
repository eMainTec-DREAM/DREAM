package intf.dream.android.online.iss.service;

import java.util.List;
import java.util.Map;

/**
 *  service
 * @author  
 * @version $Id:  $
 * @since   1.0
 */
public interface AnOnIssListService
{     
    public List findIssList(Map map) throws Exception;
    public int deleteIss(List list) throws Exception;
    public int insertIss(List list) throws Exception;
    public int updateIss(List list) throws Exception;
}
