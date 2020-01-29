package dream.pers.priv.info.dao;

import common.bean.User;
import dream.pers.priv.info.dto.MaLinkMenuDetailDTO;
import dream.pers.priv.info.dto.MaMyInfoCommonDTO;


/**
 * »ó¼¼ dao
 * @author  jung7126
 * @version $Id: MaLinkMenuDetailDAO.java,v 1.0 2015/12/04 08:10:27 jung7126 Exp $
 * @since   1.0
 */
public interface MaLinkMenuDetailDAO
{
    /**
     * detail find
     * @author jung7126
     * @version $Id: MaLinkMenuDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param pageId
     * @param grdColId
     * @param user 
     * @return
     */
    public MaLinkMenuDetailDTO findDetail(MaMyInfoCommonDTO maMyInfoCommonDTO, User user);

    /**
     * detail update
     * @author jung7126
     * @version $Id: MaLinkMenuDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param maLinkMenuDetailDTO
     * @param maMyInfoCommonDTO
     * @return
     */
    public int updateDetail(MaLinkMenuDetailDTO maLinkMenuDetailDTO, MaMyInfoCommonDTO maMyInfoCommonDTO);
    
    /**
     * detail insert
     * @author jung7126
     * @version $Id: MaLinkMenuDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param maLinkMenuDetailDTO
     * @param maMyInfoCommonDTO
     * @return
     */
    public int insertDetail(MaLinkMenuDetailDTO maLinkMenuDetailDTO, MaMyInfoCommonDTO maMyInfoCommonDTO);
}