package dream.pers.appln.service;

import common.bean.User;

import dream.pers.appln.dto.MaAppLineCommonDTO;
import dream.pers.appln.dto.MaAppLineDetailDTO;

/**
 * »ó¼¼ service
 * 
 * @author kim21017
 * @version $Id: MaAppLineDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface MaAppLineDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaAppLineDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maAppLineCommonDTO
     * @return
     * @throws Exception
     */
    public MaAppLineDetailDTO findDetail(MaAppLineCommonDTO maAppLineCommonDTO, User user)throws Exception;

    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaAppLineDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maAppLineDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaAppLineDetailDTO maAppLineDetailDTO, User user) throws Exception;
    
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaAppLineDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maAppLineDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaAppLineDetailDTO maAppLineDetailDTO, User user) throws Exception;
    /**
     * detail confirm
     * @author kim21017
     * @version $Id: MaAppLineDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maAppLineDetailDTO
     * @return
     * @throws Exception
     */
    public int confirmDetail(MaAppLineDetailDTO maAppLineDetailDTO, User user) throws Exception;
}
