package intf.dream.android.online.finder.equip.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface AnOnFinderEquipListDAO extends  BaseJdbcDaoSupportIntf
{
    public List findEquipList(Map map) throws Exception;
}