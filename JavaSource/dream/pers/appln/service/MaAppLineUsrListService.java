package dream.pers.appln.service;

import java.util.List;

import common.bean.User;
import dream.pers.appln.dto.MaAppLineCommonDTO;
import dream.pers.appln.dto.MaAppLineUsrListDTO;

/**
 * ¸ñ·Ï
 * @author  kim21017
 * @version $Id: MaAppLineUsrListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaAppLineUsrListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaAppLineUsrListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maAppLineCommonDTO
     * @param maAppLineUsrListDTO
     * @throws Exception
     */
    public List findAnsList(MaAppLineCommonDTO maAppLineCommonDTO, MaAppLineUsrListDTO maAppLineUsrListDTO, User user);
    /**
     *  delete
     * @author  kim21017
     * @version $Id: MaAppLineUsrListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param strings
     * @param d_id 
     * @throws Exception
     */
    public int deleteAnsList(String[] m_id, String[] d_id) throws Exception;

    public String findTotalCount(MaAppLineCommonDTO maAppLineCommonDTO, MaAppLineUsrListDTO maAppLineUsrListDTO, User user) throws Exception;

}
