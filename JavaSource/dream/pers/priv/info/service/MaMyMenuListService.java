package dream.pers.priv.info.service;

import java.util.List;

import common.bean.User;
import dream.pers.priv.info.dto.MaMyInfoCommonDTO;

/**
 * Ä®·³ ¸ñ·Ï
 * @author  jung7126
 * @version $Id: MaMyMenuListService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
 * @since   1.0
 */
public interface MaMyMenuListService
{     
    /**
     *  grid find
     * @author  jung7126
     * @version $Id: MaMyMenuListService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
     * @since   1.0
     * 
     * @param pageId
     * @param user 
     * @throws Exception
     */
    public List findList(MaMyInfoCommonDTO maMyInfoCommonDTO, User user);
    /**
     *  delete
     * @author  jung7126
     * @version $Id: MaMyMenuListService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @throws Exception
     */
    public int deleteList(String[] deleteRows) throws Exception;

}
