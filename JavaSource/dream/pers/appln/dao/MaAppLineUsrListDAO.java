package dream.pers.appln.dao;

import java.util.List;

import common.bean.User;
import dream.pers.appln.dto.MaAppLineCommonDTO;
import dream.pers.appln.dto.MaAppLineUsrListDTO;

/**
 * ¸ñ·Ï dao
 * @author  kim21017
 * @version $Id: MaAppLineUsrListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaAppLineUsrListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaAppLineUsrListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maAppLineCommonDTO
     * @param maAppLineUsrListDTO
     * @return List
     */
    public List findAnsList(MaAppLineCommonDTO maAppLineCommonDTO, MaAppLineUsrListDTO maAppLineUsrListDTO, User user);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaAppLineUsrListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRow
     * @return
     */
    public int deleteAnsList(String deleteRow);
    
    public String findTotalCount(MaAppLineCommonDTO maAppLineCommonDTO, MaAppLineUsrListDTO maAppLineUsrListDTO, User user) throws Exception;

}