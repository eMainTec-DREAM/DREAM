package dream.pers.priv.info.dao;

import java.util.List;

import common.bean.User;
import dream.pers.priv.info.dto.MaMyInfoCommonDTO;

/**
 *  ¸ñ·Ï dao
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface PersPrivInfoMsgEmpListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param maMyInfoCommonDTO
     * @param user 
     * @return List
     */
    public List findList(MaMyInfoCommonDTO maMyInfoCommonDTO, User loginUser);
    
    /**
     * delete
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteList(String id, User user);
    
    public String findTotalCount(MaMyInfoCommonDTO maMyInfoCommonDTO, User user) throws Exception;
}