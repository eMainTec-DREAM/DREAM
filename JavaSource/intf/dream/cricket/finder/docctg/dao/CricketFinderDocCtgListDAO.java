package intf.dream.cricket.finder.docctg.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface CricketFinderDocCtgListDAO extends  BaseJdbcDaoSupportIntf
{
    public List findDocCtgList(Map map) throws Exception;
}