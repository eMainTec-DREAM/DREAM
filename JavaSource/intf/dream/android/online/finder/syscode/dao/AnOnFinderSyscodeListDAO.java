package intf.dream.android.online.finder.syscode.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface AnOnFinderSyscodeListDAO extends  BaseJdbcDaoSupportIntf
{
    public List findSyscodeList(Map map) throws Exception;
}