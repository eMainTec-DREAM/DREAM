package dream.consult.program.page.dao;

import java.util.List;

import common.bean.User;
import dream.consult.program.page.dto.MaGrdMngCommonDTO;
import dream.consult.program.page.dto.MaPgMngCommonDTO;

/**
 * ¸ñ·Ï dao
 * @author  jung7126
 * @version $Id: MaGrdMngListDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
 * @since   1.0
 */
public interface MaGrdMngListDAO
{
    /**
     * grid find
     * @author  jung7126
     * @version $Id: MaGrdMngListDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
     * @since   1.0
     * 
     * @param maGrdMngCommonDTO
     * @return List
     */
    public List findGrdList(MaPgMngCommonDTO maPgMngCommonDTO,MaGrdMngCommonDTO maGrdMngCommonDTO);
    
    /**
     * delete
     * @author jung7126
     * @version $Id: MaGrdMngListDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param maGrdMngDTOList
     * @return
     */
    public int deletePage(String id);
    
    public String findTotalCount(MaPgMngCommonDTO maPgMngCommonDTO,MaGrdMngCommonDTO maGrdMngCommonDTO, User user) throws Exception;
    
}