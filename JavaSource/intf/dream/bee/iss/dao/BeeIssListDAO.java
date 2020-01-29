package intf.dream.bee.iss.dao;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportIntf;
import intf.dream.bee.iss.dto.BeeIssCommonDTO;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface BeeIssListDAO extends  BaseJdbcDaoSupportIntf
{
    public List findIssList(BeeIssCommonDTO beeIssCommonDTO, Map map) throws Exception;
    public List findIssCount(BeeIssCommonDTO beeIssCommonDTO, Map map) throws Exception;
    public int deleteIss(Map map) throws Exception;
    public int insertIssHdr(Map map) throws Exception;
    public int insertIss(Map map) throws Exception;
    public int updateIss(Map map) throws Exception;
}