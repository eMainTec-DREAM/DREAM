package intf.dream.bee.check.dao;

import java.util.List;
import java.util.Map;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface BeeCheckDAO
{
    public List deviceCheck(Map map);
    public List skinCheck(Map map);
    public List beeVersionCheck(Map map);
    public List logoCheck(Map map);
}