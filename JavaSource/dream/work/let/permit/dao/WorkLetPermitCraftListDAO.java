package dream.work.let.permit.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.work.let.permit.dto.WorkLetPermitCraftListDTO;
import dream.work.let.permit.dto.WorkLetPermitDetailDTO;

/**
 * 안전작업허가서유형 - 작업자 목록 dao
 * @author  syyang
 * @version $Id: WorkLetPermitCraftListDAO.java,v 1.0 2015/12/02 09:14:12 syyang Exp $
 * @since   1.0
 */
public interface WorkLetPermitCraftListDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * grid find
     * @author  syyang
     * @version $Id: WorkLetPermitCraftListDAO.java,v 1.0 2015/12/02 09:14:12 syyang Exp $
     * @since   1.0
     * 
     * @param workLetCommonDTO
     * @param workLetPermitCraftListDTO
     * @param loginUser
     * @return List
     */
    public List findCraftList(WorkLetPermitDetailDTO workLetPermitDetailDTO, WorkLetPermitCraftListDTO workLetPermitCraftListDTO, User loginUser);
    
    /**
     * delete
     * @author syyang
     * @version $Id: WorkLetPermitCraftListDAO.java,v 1.0 20155/12/02 08:25:47 syyang Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteCraftList(String id, String compNo);
    
    public String findTotalCount(WorkLetPermitDetailDTO workLetPermitDetailDTO, WorkLetPermitCraftListDTO workLetPermitCraftListDTO, User user) throws Exception;
}