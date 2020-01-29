package dream.mgr.lang.dao;

import java.util.List;

import common.bean.User;
import dream.mgr.lang.dto.MaResCommonDTO;

/**
 * 언어 - 목록 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface MaResListDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maResCommonDTO
     * @return List
     */
    public List findResList(MaResCommonDTO maResCommonDTO, User user);

    public String findTotalCount(MaResCommonDTO maResCommonDTO, User user);
}