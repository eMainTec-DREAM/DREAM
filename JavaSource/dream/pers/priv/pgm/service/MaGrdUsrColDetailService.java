package dream.pers.priv.pgm.service;

import common.bean.User;
import dream.consult.program.page.dto.MaGrdUsrColDetailDTO;
import dream.pers.priv.pgm.dto.MaGrdUsrCommonDTO;
import dream.pers.priv.pgm.dto.MaGrdUsrDetailDTO;

/**
 * Ä®·³ »ó¼¼
 * @author  jung7126
 * @version $Id: MaGrdUsrColDetailService.java,v 1.0 2015/12/04 09:08:29 jung7126 Exp $
 * @since   1.0
 */
public interface MaGrdUsrColDetailService
{    
    /**
     * detail find
     * @author jung7126
     * @version $Id: MaGrdUsrColDetailService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
     * @since   1.0
     * 
     * @param pageId
     * @param grdColId
     * @param user 
     * @return
     * @throws Exception
     */
    public MaGrdUsrColDetailDTO findDetail(String pageId, String grdColId, User user)throws Exception;
    /**
     * detail update
     * @author jung7126
     * @version $Id: MaGrdUsrColDetailService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
     * @since   1.0
     * 
     * @param maGrdUsrColDetailDTO
     * @param maGrdUsrCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaGrdUsrColDetailDTO maGrdUsrColDetailDTO, MaGrdUsrCommonDTO maGrdUsrCommonDTO) throws Exception;
    /**
     * detail insert
     * @author jung7126
     * @version $Id: MaGrdUsrColDetailService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
     * @since   1.0
     * 
     * @param maGrdUsrColDetailDTO
     * @param maGrdUsrCommonDTO
     * @param user 
     * @param maGrdUsrDetailDTO 
     * @return
     * @throws Exception
     */
    public int insertDetail(MaGrdUsrColDetailDTO maGrdUsrColDetailDTO, MaGrdUsrCommonDTO maGrdUsrCommonDTO, User user, MaGrdUsrDetailDTO maGrdUsrDetailDTO) throws Exception;
}
