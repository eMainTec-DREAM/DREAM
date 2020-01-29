package intf.dream.bee.check.service;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.List;
import java.util.Map;

import com.microsoft.azure.storage.StorageException;

/**
 *  service
 * @author  
 * @version $Id:  $
 * @since   1.0
 */
public interface BeeCheckService
{
    public List deviceCheck(Map map);
    public List skinCheck(Map map);
    public List beeVersionCheck(Map map);
    public List logoCheck(Map map) throws InvalidKeyException, URISyntaxException, StorageException;
}
