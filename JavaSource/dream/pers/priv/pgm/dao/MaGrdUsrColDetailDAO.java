package dream.pers.priv.pgm.dao;

import common.bean.User;
import dream.consult.program.page.dto.MaGrdUsrColDetailDTO;
import dream.pers.priv.pgm.dto.MaGrdUsrCommonDTO;

/**
 * Ä®·³ »ó¼¼ dao
 * @author  jung7126
 * @version $Id: MaGrdUsrColDetailDAO.java,v 1.0 2015/12/04 08:10:27 jung7126 Exp $
 * @since   1.0
 */
public interface MaGrdUsrColDetailDAO
{
    /**
     * detail find
     * @author jung7126
     * @version $Id: MaGrdUsrColDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param pageId
     * @param grdColId
     * @param user 
     * @return
     */
    public MaGrdUsrColDetailDTO findDetail(String pgGridColId, String usrGridColId, User user);

    /**
     * detail update
     * @author jung7126
     * @version $Id: MaGrdUsrColDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param maGrdUsrColDetailDTO
     * @param maGrdUsrCommonDTO
     * @return
     */
    public int updateDetail(MaGrdUsrColDetailDTO maGrdUsrColDetailDTO, MaGrdUsrCommonDTO maGrdUsrCommonDTO);
    
    /**
     * detail insert
     * @author jung7126
     * @version $Id: MaGrdUsrColDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param maGrdUsrColDetailDTO
     * @param maGrdUsrCommonDTO
     * @param user 
     * @return
     */
    public int insertDetail(MaGrdUsrColDetailDTO maGrdUsrColDetailDTO, MaGrdUsrCommonDTO maGrdUsrCommonDTO, User user);
    
    public MaGrdUsrColDetailDTO findUsrDetail(String pgGridId, String usrPgGridColId, User user);
}