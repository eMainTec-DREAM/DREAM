package dream.pers.priv.notice.dao;

import java.util.List;

import common.bean.User;
import dream.pers.priv.info.dto.MaMyInfoCommonDTO;

/**
 * Notice 설정 - 목록 Dao
 * @author  nhkim8548
 * @version $Id$
 * @since   1.0
 */
public interface PersPrivNoticeListDAO
{
    /**
     * grid find
     * @author  nhkim8548
     * @version $Id$
     * @since   1.0
     * 
     * @param maMyInfoCommonDTO
     * @param user 
     * @return List
     */
    public List findList(MaMyInfoCommonDTO maMyInfoCommonDTO, User loginUser);
    
    public String findTotalCount(MaMyInfoCommonDTO maMyInfoCommonDTO, User user) throws Exception;
}