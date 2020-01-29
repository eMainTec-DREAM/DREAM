package dream.mgr.intf.service;

import java.util.List;
import java.util.Map;

import com.fins.org.json.JSONObject;

import common.bean.User;
import dream.mgr.intf.dto.MgrIntfCommonDTO;

/**
 * 인터페이스 파라미터
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface MgrIntfParamsService
{    
    public List findList(MgrIntfCommonDTO mgrIntfCommonDTO, User user) throws Exception;

    public JSONObject execList(List<Map> gridList, MgrIntfCommonDTO mgrIntfCommonDTO, User user) throws Exception;    
}
