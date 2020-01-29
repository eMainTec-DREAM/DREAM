package intf.dream.cricket.initcode.dao;

import java.util.List;
import java.util.Map;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface CricketInitcodeListDAO
{
    public List findLangList(Map map);
    public List findSyscodeList(Map map);
}