package intf.dream.android.online.image.service;

import java.util.List;
import java.util.Map;

import org.apache.struts.upload.FormFile;

/**
 *  service
 * @author  
 * @version $Id:  $
 * @since   1.0
 */
public interface AnOnImageListService
{     
    public List findImageList(Map map) throws Exception;
    public int insertImage(List list, FormFile[] fileList)  throws Exception;
    public int updateImage(List list, FormFile[] fileList) throws Exception;
    public int deleteImage(List list) throws Exception;
}
