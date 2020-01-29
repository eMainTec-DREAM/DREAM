package intf.dream.bee.pmwork.service;

import java.util.List;
import java.util.Map;

import org.apache.struts.upload.FormFile;

import intf.dream.bee.pmwork.dto.BeePmworkCommonDTO;

/**
 *  service
 * @author  
 * @version $Id:  $
 * @since   1.0
 */
public interface BeePmworkListService
{     
    public List findPmworkList(BeePmworkCommonDTO beePmworkCommonDTO, Map map) throws Exception;
    public List findPmworkCount(BeePmworkCommonDTO beePmworkCommonDTO, Map map) throws Exception;

    public int deletePmwork(List list) throws Exception;
    public int insertPmwork(List list) throws Exception;
    public int updatePmwork(List list) throws Exception;
    
    public List findWoCraftList(Map map) throws Exception;
    public int insertWoCraft(List list) throws Exception;
    public int updateWoCraft(List list) throws Exception;
    public int deleteWoCraft(List list) throws Exception;
    
    public List findWoPartsList(Map map) throws Exception;
    public int insertWoParts(List list) throws Exception;
    public int updateWoParts(List list) throws Exception;
    public int deleteWoParts(List list) throws Exception;
    public List findStockQty(Map map) throws Exception;

    public List findPhotoList(Map map) throws Exception;
    public int insertPhoto(List list,FormFile[] fileList) throws Exception;
    public int updatePhoto(List list,FormFile[] fileList) throws Exception;
    public int updateDocument(List list,FormFile[] fileList) throws Exception;
    public int deletePhoto(List list) throws Exception;
    public int deleteTempPhoto(List list) throws Exception;
}
