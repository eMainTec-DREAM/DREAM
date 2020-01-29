package dream.work.let.dao;

import java.util.List;

import common.bean.User;
import dream.work.let.dto.WorkLetCommonDTO;

/**
 * 안전작업 - 목록 dao
 * @author  syyang
 * @version $Id: WorkLetListDAO.java,v 1.0 2015/12/02 09:14:12 syyang Exp $
 * @since   1.0
 */
public interface WorkLetListDAO
{
    /**
     * grid find
     * @author  syyang
     * @version $Id: WorkLetListDAO.java,v 1.0 2015/12/02 09:14:12 syyang Exp $
     * @since   1.0
     * 
     * @param workLetCommonDTO
     * @return List
     */
    public List findWoLetList(WorkLetCommonDTO workLetCommonDTO, User user);
    
    /**
     * delete
     * @author syyang
     * @version $Id: WorkLetListDAO.java,v 1.0 20155/12/02 08:25:47 syyang Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteWoLet(String id, User user);
    
    public String findTotalCount(WorkLetCommonDTO workLetCommonDTO,User user, String woType);
	
}