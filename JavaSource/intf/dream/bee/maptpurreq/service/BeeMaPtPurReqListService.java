package intf.dream.bee.maptpurreq.service;

import java.util.List;
import java.util.Map;

import intf.dream.bee.maptpurreq.dto.BeeMaPtPurReqCommonDTO;

/**
 *  service
 * @author  
 * @version $Id:  $
 * @since   1.0
 */
public interface BeeMaPtPurReqListService
{     
    public List findMaPtPurReqList(BeeMaPtPurReqCommonDTO beeMaPtPurReqCommonDTO, Map map) throws Exception;
    public List findMaPtPurReqCount(BeeMaPtPurReqCommonDTO beeMaPtPurReqCommonDTO, Map map) throws Exception;
    public int deleteMaPtPurReq(List list) throws Exception;
    public int insertMaPtPurReq(List list) throws Exception;
    public int updateMaPtPurReq(List list) throws Exception;
    public int confirmMaPtPurReq(List list) throws Exception;
}
