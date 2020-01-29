package intf.dream.cricket.check.dao;

import java.util.List;
import java.util.Map;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface CricketCheckDAO
{
    public List deviceCheck(Map map);
    public List skinCheck(Map map);
    public List cricketVersionCheck(Map map);
    public List logoCheck(Map map);
}