package intf.dream.bee.woreq.service;

import java.util.List;
import java.util.Map;

import org.apache.struts.upload.FormFile;

import intf.dream.bee.woreq.dto.BeeWoReqCommonDTO;

/**
 *  service
 * @author  
 * @version $Id:  $
 * @since   1.0
 */
public interface BeeWoReqListService
{     
    public List findWoReqList(BeeWoReqCommonDTO beeWoReqCommonDTO, Map map) throws Exception;
    public List findWoReqCount(BeeWoReqCommonDTO beeWoReqCommonDTO, Map map) throws Exception;

    public int deleteWoReq(List list) throws Exception;
    public int insertWoReq(List list) throws Exception;
    public int updateWoReq(List list) throws Exception;
    
    public List findWoResList(BeeWoReqCommonDTO beeWoReqCommonDTO, Map map) throws Exception;
    public List findWoResCount(BeeWoReqCommonDTO beeWoReqCommonDTO, Map map) throws Exception;
    public int updateWoRes(List list) throws Exception;
    public int deleteWoRes(List list) throws Exception;
    
    public List findWoReqResList(Map map) throws Exception;
    public int insertWoReqRes(List list) throws Exception;
    public int updateWoReqRes(List list) throws Exception;
    public int deleteWoReqRes(List list) throws Exception;

    public List findPhotoList(Map map) throws Exception;
    public int insertPhoto(List list,FormFile[] fileList) throws Exception;
    public int updatePhoto(List list,FormFile[] fileList) throws Exception;
    public int deletePhoto(List list) throws Exception;
    public int deleteTempPhoto(List list) throws Exception;
}
