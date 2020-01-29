package dream.consult.program.page.dao;

import java.util.List;

import common.bean.User;
import dream.consult.program.page.dto.MaGrdMngCommonDTO;

/**
 * Ä®·³ ¸ñ·Ï dao
 * @author  jung7126
 * @version $Id: MaGrdMngColListDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
 * @since   1.0
 */
public interface MaGrdMngColListDAO
{
    /**
     * grid find
     * @author  jung7126
     * @version $Id: MaGrdMngColListDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
     * @since   1.0
     * 
     * @param maGrdMngCommonDTO
     * @param user 
     * @return List
     */
    public List findColList(MaGrdMngCommonDTO maGrdMngCommonDTO, User user);
    
    /**
     * delete
     * @author jung7126
     * @version $Id: MaGrdMngColListDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteColList(String id);

    public int sysYColList(String id);

    public int sysNColList(String id);

    public String findTotalCount(MaGrdMngCommonDTO maGrdMngCommonDTO, User user) throws Exception;
}