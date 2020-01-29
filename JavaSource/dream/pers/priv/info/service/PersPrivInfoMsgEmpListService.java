package dream.pers.priv.info.service;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.pers.priv.info.dto.MaMyInfoCommonDTO;

/**
 * ¸ñ·Ï
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface PersPrivInfoMsgEmpListService
{     
    /**
     *  grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param pageId
     * @param user 
     * @throws Exception
     */
    public List findList(MaMyInfoCommonDTO maMyInfoCommonDTO, User user);
    /**
     *  delete
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param deleteRows
     * @throws Exception
     */
    public int deleteList(String[] deleteRows, User user) throws Exception;

    public String findTotalCount(MaMyInfoCommonDTO maMyInfoCommonDTO, User user) throws Exception;
    
    public void saveList(List<Map> gridList, User user) throws Exception;
}
