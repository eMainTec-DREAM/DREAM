package intf.dream.android.macheck.dao;

import java.util.List;
import java.util.Map;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface AnIfCheckDAO
{
    public List deviceCheck(Map map);
    public List skinCheck(Map map);
    public List versionCheck(Map map);
    public List antVersionCheck(Map map);
    public List beeVersionCheck(Map map);
    public List cricketVersionCheck(Map map);
    public List logoCheck(Map map);
}