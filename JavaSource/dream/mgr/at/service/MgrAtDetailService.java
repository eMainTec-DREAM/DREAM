package dream.mgr.at.service;

import common.bean.User;
import dream.mgr.at.dto.MgrAtCommonDTO;
import dream.mgr.at.dto.MgrAtDetailDTO;

/**
 * »ó¼¼ service
 * 
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 */
public interface MgrAtDetailService
{   
	/**
     * find detail
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrAtDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public MgrAtDetailDTO findDetail(MgrAtCommonDTO mgrAtCommonDTO, User user) throws Exception;
}
