package dream.asset.list.dao;

import java.util.List;

import common.bean.User;
import dream.asset.list.dto.MaEqMstrHistListDTO;

/**
 * 설비변경이력 - 목록 dao
 * @author  kim21017
 * @version $Id: MaEqMstrHistListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaEqMstrHistListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaEqMstrHistListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrHistListDTO
     * @return List
     */
    public List findEqMstrHistList(MaEqMstrHistListDTO maEqMstrHistListDTO, User user);
    
}