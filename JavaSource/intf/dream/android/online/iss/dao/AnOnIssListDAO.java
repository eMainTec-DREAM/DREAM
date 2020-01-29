package intf.dream.android.online.iss.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface AnOnIssListDAO extends  BaseJdbcDaoSupportIntf
{
    public List findIssList(Map map) throws Exception;
    public int deleteIss(Map map) throws Exception;
    public int insertIssHdr(Map map) throws Exception;
    public int insertIss(Map map) throws Exception;
    public int updateIss(Map map) throws Exception;
}