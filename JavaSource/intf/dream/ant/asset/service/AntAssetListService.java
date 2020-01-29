package intf.dream.ant.asset.service;

import java.util.List;
import java.util.Map;

/**
 *  service
 * @author  
 * @version $Id:  $
 * @since   1.0
 */
public interface AntAssetListService
{     
    public List findAssetList(Map map) throws Exception;
    public List findAssetFileList(Map map) throws Exception;

    public int saveAssetList(List list) throws Exception;
}
