package dream.mgr.at.service;

import java.util.List;

import common.bean.User;
import dream.mgr.at.dto.MgrAtCommonDTO;
import dream.mgr.at.dto.MgrAtHistListDTO;

/**
 * Audit Trail Hist Page - List Service
 * @author youngjoo38
 * @version $Id: MgrAtHistListService.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 */
public interface MgrAtHistListService
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
     * find Total Count
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param mgrAtHistListDTO
     * @return
     */
    public String findTotalCount(MgrAtCommonDTO mgrAtCommonDTO, MgrAtHistListDTO mgrAtHistListDTO, User user) throws Exception;
}
