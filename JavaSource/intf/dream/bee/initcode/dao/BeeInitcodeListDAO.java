package intf.dream.bee.initcode.dao;

import java.util.List;
import java.util.Map;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface BeeInitcodeListDAO
{
    public List findLangList(Map map);
    public List findSyscodeList(Map map);
}