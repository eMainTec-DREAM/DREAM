package dream.mgr.cccd.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.mgr.cccd.dto.LovCtCtrListDTO;

/**
 * CP검색 팝업
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface LovCtCtrListDAO
{
    /**
     * CP 검색
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovCtCtrListDTO
     * @return
     */
    public List findCtCtrList(LovCtCtrListDTO lovCtCtrListDTO, User loginUser);

    public List findCtCtrACList(LovCtCtrListDTO lovCtCtrListDTO, User user, Map<String, String> conditionMap);
    
    public String findTotalCount(LovCtCtrListDTO lovCtCtrListDTO, User user, Map<String, String> conditionMap);
}