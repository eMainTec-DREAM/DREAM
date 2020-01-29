package intf.dream.bee.iss.service;

import java.util.List;
import java.util.Map;

import intf.dream.bee.iss.dto.BeeIssCommonDTO;

/**
 *  service
 * @author  
 * @version $Id:  $
 * @since   1.0
 */
public interface BeeIssListService
{
    public List findIssList(BeeIssCommonDTO beeIssCommonDTO, Map map) throws Exception;
    public List findIssCount(BeeIssCommonDTO beeIssCommonDTO, Map map) throws Exception;
    public int deleteIss(List list) throws Exception;
    public int insertIss(List list) throws Exception;
    public int updateIss(List list) throws Exception;
}
