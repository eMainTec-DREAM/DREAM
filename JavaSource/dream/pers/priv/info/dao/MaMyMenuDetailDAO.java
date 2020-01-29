package dream.pers.priv.info.dao;

import common.bean.User;
import dream.pers.priv.info.dto.MaMyInfoCommonDTO;
import dream.pers.priv.info.dto.MaMyMenuDetailDTO;


/**
 * »ó¼¼ dao
 * @author  jung7126
 * @version $Id: MaMyMenuDetailDAO.java,v 1.0 2015/12/04 08:10:27 jung7126 Exp $
 * @since   1.0
 */
public interface MaMyMenuDetailDAO
{
    /**
     * detail find
     * @author jung7126
     * @version $Id: MaMyMenuDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param pageId
     * @param grdColId
     * @param user 
     * @return
     */
    public MaMyMenuDetailDTO findDetail(MaMyInfoCommonDTO maMyInfoCommonDTO, User user);

    /**
     * detail update
     * @author jung7126
     * @version $Id: MaMyMenuDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param maMyMenuDetailDTO
     * @param maMyInfoCommonDTO
     * @return
     */
    public int updateDetail(MaMyMenuDetailDTO maMyMenuDetailDTO, MaMyInfoCommonDTO maMyInfoCommonDTO);
    
    /**
     * detail insert
     * @author jung7126
     * @version $Id: MaMyMenuDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param maMyMenuDetailDTO
     * @param maMyInfoCommonDTO
     * @return
     */
    public int insertDetail(MaMyMenuDetailDTO maMyMenuDetailDTO, MaMyInfoCommonDTO maMyInfoCommonDTO);
}