package dream.pers.priv.pgm.service;

import java.util.List;

import common.bean.User;
import dream.pers.priv.pgm.dto.MaGrdUsrCommonDTO;
import dream.pers.priv.pgm.dto.MaGrdUsrDetailDTO;

/**
 * Ä®·³ ¸ñ·Ï
 * @author  jung7126
 * @version $Id: MaGrdUsrColListService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
 * @since   1.0
 */
public interface MaGrdUsrColListService
{     
    /**
     *  grid find
     * @author  jung7126
     * @version $Id: MaGrdUsrColListService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
     * @since   1.0
     * 
     * @param pageId
     * @param user 
     * @throws Exception
     */
    public List findColList(MaGrdUsrCommonDTO maGrdUsrCommonDTO, MaGrdUsrDetailDTO maGrdUsrDetailDTO, User user);

    public String findTotalCount(MaGrdUsrCommonDTO maGrdUsrCommonDTO, MaGrdUsrDetailDTO maGrdUsrDetailDTO, User user);

}
