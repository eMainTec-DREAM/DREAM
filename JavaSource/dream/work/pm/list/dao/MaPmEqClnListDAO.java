package dream.work.pm.list.dao;

import java.util.List;

import common.bean.User;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;

/**
 * ���漳��  dao
 * @author  kim21017
 * @version $Id: MaPmEqClnListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaPmEqClnListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaPmEqClnListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPmMstrCommonDTO
     * @param loginUser
     * @return List
     */
    public List findClnList(MaPmMstrCommonDTO maPmMstrCommonDTO, User loginUser);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaPmEqClnListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteClnList(String id, String compNo);
    
    public String findTotalCount(MaPmMstrCommonDTO maPmMstrCommonDTO, User user) throws Exception;
}