package dream.pers.priv.info.service;

import common.bean.User;
import dream.pers.priv.info.dto.MaMyInfoCommonDTO;
import dream.pers.priv.info.dto.MaMyMenuDetailDTO;

/**
 * »ó¼¼
 * @author  jung7126
 * @version $Id: MaMyMenuDetailService.java,v 1.0 2015/12/04 09:08:29 jung7126 Exp $
 * @since   1.0
 */
public interface MaMyMenuDetailService
{    
    /**
     * detail find
     * @author jung7126
     * @version $Id: MaMyMenuDetailService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
     * @since   1.0
     * 
     * @param pageId
     * @param grdColId
     * @param user 
     * @return
     * @throws Exception
     */
    public MaMyMenuDetailDTO findDetail(MaMyInfoCommonDTO maMyInfoCommonDTO, User user)throws Exception;
    /**
     * detail update
     * @author jung7126
     * @version $Id: MaMyMenuDetailService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
     * @since   1.0
     * 
     * @param maMyMenuDetailDTO
     * @param maMyInfoCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaMyMenuDetailDTO maMyMenuDetailDTO, MaMyInfoCommonDTO maMyInfoCommonDTO) throws Exception;
    /**
     * detail insert
     * @author jung7126
     * @version $Id: MaMyMenuDetailService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
     * @since   1.0
     * 
     * @param maMyMenuDetailDTO
     * @param maMyInfoCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaMyMenuDetailDTO maMyMenuDetailDTO, MaMyInfoCommonDTO maMyInfoCommonDTO) throws Exception;
}
