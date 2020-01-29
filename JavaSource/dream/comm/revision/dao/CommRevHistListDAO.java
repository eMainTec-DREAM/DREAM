package dream.comm.revision.dao;

import java.util.List;

import common.bean.User;
import dream.comm.revision.dto.CommRevHistCommonDTO;


/**
 * 제/개정 변경이력 DAO
 * @author  kim21017
 * @version $Id: CommRevHistListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface CommRevHistListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: CommRevHistListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param commRevHistListDTO
     * @return List
     */
    public List findList(CommRevHistCommonDTO commRevHistCommonDTO, User user);
    
}