package intf.dream.bee.user.service;

import java.util.List;
import java.util.Map;

/**
 *  service
 * @author  
 * @version $Id:  $
 * @since   1.0
 */
public interface BeeUserListService
{     
    public List findList(Map map) throws Exception;
    public int updateFilter(List list) throws Exception;
}
