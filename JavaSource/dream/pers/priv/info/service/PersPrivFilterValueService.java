package dream.pers.priv.info.service;

import java.util.List;

import common.bean.User;
import dream.pers.priv.info.dto.PersPrivFilterValueDTO;

/**
 * ¸ñ·Ï
 * @author  euna0207
 * @version $Id$
 * @since   1.0
 */
public interface PersPrivFilterValueService
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
    public List<PersPrivFilterValueDTO> findList(PersPrivFilterValueDTO persPrivFilterValueDTO, User user) throws Exception;
    
    /**
     *  delete
     * @author  euna0207
     * @version $Id$
     * @since   1.0
     * 
     * @param deleteRows
     * @throws Exception
     */
    public int[] deleteList(String[] deleteRows, User user) throws Exception;

    public String findTotalCount(PersPrivFilterValueDTO persPrivFilterValueDTO, User user) throws Exception;
    
	public int insertDetail(PersPrivFilterValueDTO persPrivFilterValueDTO, User user) throws Exception;
}
