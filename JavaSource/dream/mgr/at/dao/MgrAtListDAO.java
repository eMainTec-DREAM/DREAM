package dream.mgr.at.dao;

import java.util.List;

import common.bean.User;
import dream.mgr.at.dto.MgrAtCommonDTO;


/**
 * Audit Trail DAO
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface MgrAtListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param mgrAtListDTO
     * @return List
     */
    public List findList(MgrAtCommonDTO mgrAtCommonDTO, User user);
 
    public String findTotalCount(MgrAtCommonDTO mgrAtCommonDTO, User user) throws Exception;
}