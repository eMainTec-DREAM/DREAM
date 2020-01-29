package intf.dream.bee.finder.usrcode.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface BeeFinderUsrcodeListDAO extends  BaseJdbcDaoSupportIntf
{
    public List findUsrcodeList(Map map) throws Exception;
}