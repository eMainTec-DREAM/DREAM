package dream.mgr.at.dao;

import java.util.List;

import common.bean.User;
import dream.mgr.at.dto.MgrAtCommonDTO;
import dream.mgr.at.dto.MgrAtHistListDTO;

/**
 * MgrAtHist Page - List DAO
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 *
 */
public interface MgrAtHistListDAO
{
    /**
     * FIND LIST
     * @param mgrAtHistListDTO
     * @param user
     * @return
     * @throws Exception
     */

    
    public List findList(MgrAtCommonDTO mgrAtCommonDTO, MgrAtHistListDTO mgrAtHistListDTO, User user) throws Exception;
    
    /**
     * DELETE LIST
     * @param mgrAtHistListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(MgrAtCommonDTO mgrAtCommonDTO, MgrAtHistListDTO mgrAtHistListDTO, User user) throws Exception;
}
