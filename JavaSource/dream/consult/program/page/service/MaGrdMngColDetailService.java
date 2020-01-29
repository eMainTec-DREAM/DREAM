package dream.consult.program.page.service;

import common.bean.User;
import dream.consult.program.page.dto.MaGrdMngColDetailDTO;
import dream.consult.program.page.dto.MaGrdMngCommonDTO;

/**
 * Ä®·³ »ó¼¼
 * @author  jung7126
 * @version $Id: MaGrdMngColDetailService.java,v 1.0 2015/12/04 09:08:29 jung7126 Exp $
 * @since   1.0
 */
public interface MaGrdMngColDetailService
{    
    /**
     * detail find
     * @author jung7126
     * @version $Id: MaGrdMngColDetailService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
     * @since   1.0
     * 
     * @param pageId
     * @param grdColId
     * @param user 
     * @return
     * @throws Exception
     */
    public MaGrdMngColDetailDTO findDetail(String pageId, String grdColId, User user)throws Exception;
    /**
     * detail update
     * @author jung7126
     * @version $Id: MaGrdMngColDetailService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
     * @since   1.0
     * 
     * @param maGrdMngColDetailDTO
     * @param maGrdMngCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaGrdMngColDetailDTO maGrdMngColDetailDTO, MaGrdMngCommonDTO maGrdMngCommonDTO) throws Exception;
    /**
     * detail insert
     * @author jung7126
     * @version $Id: MaGrdMngColDetailService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
     * @since   1.0
     * 
     * @param maGrdMngColDetailDTO
     * @param maGrdMngCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaGrdMngColDetailDTO maGrdMngColDetailDTO, MaGrdMngCommonDTO maGrdMngCommonDTO) throws Exception;
}
