package dream.pers.priv.pgm.dao;

import java.util.List;

import common.bean.User;
import dream.pers.priv.pgm.dto.MaGrdUsrCommonDTO;
import dream.pers.priv.pgm.dto.MaGrdUsrDetailDTO;

/**
 * Ä®·³ ¸ñ·Ï dao
 * @author  jung7126
 * @version $Id: MaGrdUsrColListDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
 * @since   1.0
 */
public interface MaGrdUsrColListDAO
{
    /**
     * grid find
     * @author  jung7126
     * @version $Id: MaGrdUsrColListDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
     * @param maGrdUsrCommonDTO 
     * @since   1.0
     * 
     * @param page_id
     * @param user 
     * @return List
     */
    public List findColList(MaGrdUsrDetailDTO maGrdUsrDetailDTO, MaGrdUsrCommonDTO maGrdUsrCommonDTO, User user);

    public List findUserColList(MaGrdUsrDetailDTO maGrdUsrDetailDTO, MaGrdUsrCommonDTO maGrdUsrCommonDTO, User user);

    public String findUserColCount(MaGrdUsrDetailDTO maGrdUsrDetailDTO, MaGrdUsrCommonDTO maGrdUsrCommonDTO, User user);

    public String findColCount(MaGrdUsrDetailDTO maGrdUsrDetailDTO, MaGrdUsrCommonDTO maGrdUsrCommonDTO, User user);
}