package dream.pers.priv.pgm.service;

import common.bean.User;
import dream.pers.priv.pgm.dto.MaGrdUsrCommonDTO;
import dream.pers.priv.pgm.dto.MaGrdUsrDetailDTO;

/**
 * Ä®·³ »ó¼¼
 * @author  jung7126
 * @version $Id: MaGrdUsrDetailService.java,v 1.0 2015/12/04 09:08:29 jung7126 Exp $
 * @since   1.0
 */
public interface MaGrdUsrDetailService
{    
    /**
     * detail find
     * @author jung7126
     * @version $Id: MaGrdUsrDetailService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
     * @since   1.0
     * 
     * @param maGrdUsrCommonDTO
     * @param user 
     * @return
     * @throws Exception
     */
    public MaGrdUsrDetailDTO findDetail(MaGrdUsrCommonDTO maGrdUsrCommonDTO, User user)throws Exception;
    /**
     * detail update
     * @author jung7126
     * @version $Id: MaGrdUsrDetailService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
     * @since   1.0
     * 
     * @param maGrdUsrDetailDTO
     * @param maGrdUsrCommonDTO
     * @param user 
     * @return
     * @throws Exception
     */
    public int updateDetail(MaGrdUsrDetailDTO maGrdUsrDetailDTO, MaGrdUsrCommonDTO maGrdUsrCommonDTO, User user) throws Exception;
    /**
     * detail insert
     * @author jung7126
     * @version $Id: MaGrdUsrDetailService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
     * @since   1.0
     * 
     * @param maGrdUsrDetailDTO
     * @param maGrdUsrCommonDTO
     * @param user 
     * @return
     * @throws Exception
     */
    public int insertDetail(MaGrdUsrDetailDTO maGrdUsrDetailDTO, MaGrdUsrCommonDTO maGrdUsrCommonDTO, User user) throws Exception;
}
