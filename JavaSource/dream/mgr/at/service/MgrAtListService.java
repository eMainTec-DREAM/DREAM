package dream.mgr.at.service;

import java.util.List;

import common.bean.User;
import dream.mgr.at.dto.MgrAtCommonDTO;


/**
 * Audit Trail service
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface MgrAtListService
{     
    /**
     *  grid find
     * @author  youngjoo38
     * @version $Id$
     * @param mgrAtListDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findList(MgrAtCommonDTO mgrAtCommonDTO, User user);    
    
    public String findTotalCount(MgrAtCommonDTO mgrAtCommonDTO, User user) throws Exception;
}
