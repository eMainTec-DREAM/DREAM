package dream.pers.priv.notice.service;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.pers.priv.info.dto.MaMyInfoCommonDTO;

/**
 * Notice 설정 - 목록 Service
 * @author  nhkim8548
 * @version $Id$
 * @since   1.0
 */
public interface PersPrivNoticeListService
{     
    /**
     * grid find
     * @author  nhkim8548
     * @version $Id$
     * @since   1.0
     * 
     * @param pageId
     * @param user 
     * @throws Exception
     */
    public List findList(MaMyInfoCommonDTO maMyInfoCommonDTO, User user);
    
    /**
     * list Save
     * @author  nhkim8548
     * @version $Id$
     * @since   1.0
     * 
     * @param pageId
     * @param user 
     * @throws Exception
     */
    public void saveList(List<Map> gridList, User user) throws Exception;
    
    public String findTotalCount(MaMyInfoCommonDTO maMyInfoCommonDTO, User user) throws Exception;
}
