package dream.consult.program.page.dao;

import common.bean.User;
import dream.consult.program.page.dto.MaGrdMngColDetailDTO;
import dream.consult.program.page.dto.MaGrdMngCommonDTO;

/**
 * Ä®·³ »ó¼¼ dao
 * @author  jung7126
 * @version $Id: MaGrdMngColDetailDAO.java,v 1.0 2015/12/04 08:10:27 jung7126 Exp $
 * @since   1.0
 */
public interface MaGrdMngColDetailDAO
{
    /**
     * detail find
     * @author jung7126
     * @version $Id: MaGrdMngColDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param pageId
     * @param grdColId
     * @param user 
     * @return
     */
    public MaGrdMngColDetailDTO findDetail(String pageId, String grdColId, User user);

    /**
     * detail update
     * @author jung7126
     * @version $Id: MaGrdMngColDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param maGrdMngColDetailDTO
     * @param maGrdMngCommonDTO
     * @return
     */
    public int updateDetail(MaGrdMngColDetailDTO maGrdMngColDetailDTO, MaGrdMngCommonDTO maGrdMngCommonDTO);
    
    /**
     * detail insert
     * @author jung7126
     * @version $Id: MaGrdMngColDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param maGrdMngColDetailDTO
     * @param maGrdMngCommonDTO
     * @return
     */
    public int insertDetail(MaGrdMngColDetailDTO maGrdMngColDetailDTO, MaGrdMngCommonDTO maGrdMngCommonDTO);

    public String getPgGridId(String pageId, String gridObjId);
}