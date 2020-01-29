package dream.work.let.permit.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.work.let.dto.WorkLetCommonDTO;
import dream.work.let.permit.dto.WorkLetPermitListDTO;

/**
 * 안전작업 - 안전작업허가서 목록 dao
 * @author  syyang
 * @version $Id$
 * @since   1.0
 */
public interface WorkLetPermitListDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * grid find
     * @author  syyang
     * @version $Id$
     * @since   1.0
     * 
     * @param workLetCommonDTO
     * @param workLetPermitListDTO
     * @param loginUser
     * @return List
     */
    public List findWoLetPermitList(WorkLetCommonDTO workLetCommonDTO, WorkLetPermitListDTO workLetPermitListDTO, User loginUser);
    
    /**
     * delete
     * @author syyang
     * @version $Id$
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteWoLetList(String id, String compNo);
    
    public String findTotalCount(WorkLetCommonDTO workLetCommonDTO, WorkLetPermitListDTO workLetPermitListDTO, User user) throws Exception;

}