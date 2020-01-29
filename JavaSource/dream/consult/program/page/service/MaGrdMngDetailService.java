package dream.consult.program.page.service;

import common.bean.User;
import dream.consult.program.page.dto.MaGrdMngDetailDTO;

/**
 * »ó¼¼ service
 * 
 * @author jung7126
 * @version $Id: MaGrdMngDetailService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
 * @since 1.0
 */
public interface MaGrdMngDetailService
{    
    /**
     * detail find
     * @author jung7126
     * @version $Id: MaGrdMngDetailService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
     * @since   1.0
     * 
     * @param pageId
     * @return
     * @throws Exception
     */
    public MaGrdMngDetailDTO findDetail(String pgGridId, User user)throws Exception;
    /**
     * detail insert
     * @author jung7126
     * @version $Id: MaGrdMngDetailService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
     * @since   1.0
     * 
     * @param maGrdMngDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaGrdMngDetailDTO maGrdMngDetailDTO) throws Exception;
    /**
     * detail update
     * @author jung7126
     * @version $Id: MaGrdMngDetailService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
     * @since   1.0
     * 
     * @param maGrdMngDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaGrdMngDetailDTO maGrdMngDetailDTO) throws Exception;
}
