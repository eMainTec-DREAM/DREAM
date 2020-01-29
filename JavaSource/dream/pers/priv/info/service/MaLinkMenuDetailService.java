package dream.pers.priv.info.service;

import common.bean.User;
import dream.pers.priv.info.dto.MaLinkMenuDetailDTO;
import dream.pers.priv.info.dto.MaMyInfoCommonDTO;

/**
 * »ó¼¼
 * @author  jung7126
 * @version $Id: MaLinkMenuDetailService.java,v 1.0 2015/12/04 09:08:29 jung7126 Exp $
 * @since   1.0
 */
public interface MaLinkMenuDetailService
{    
    /**
     * detail find
     * @author jung7126
     * @version $Id: MaLinkMenuDetailService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
     * @since   1.0
     * 
     * @param pageId
     * @param grdColId
     * @param user 
     * @return
     * @throws Exception
     */
    public MaLinkMenuDetailDTO findDetail(MaMyInfoCommonDTO maMyInfoCommonDTO, User user)throws Exception;
    /**
     * detail update
     * @author jung7126
     * @version $Id: MaLinkMenuDetailService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
     * @since   1.0
     * 
     * @param maLinkMenuDetailDTO
     * @param maMyInfoCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaLinkMenuDetailDTO maLinkMenuDetailDTO, MaMyInfoCommonDTO maMyInfoCommonDTO) throws Exception;
    /**
     * detail insert
     * @author jung7126
     * @version $Id: MaLinkMenuDetailService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
     * @since   1.0
     * 
     * @param maLinkMenuDetailDTO
     * @param maMyInfoCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaLinkMenuDetailDTO maLinkMenuDetailDTO, MaMyInfoCommonDTO maMyInfoCommonDTO) throws Exception;
}
