package dream.pers.appln.dao;

import common.bean.User;

import dream.pers.appln.dto.MaAppLineCommonDTO;
import dream.pers.appln.dto.MaAppLineDetailDTO;

/**
 * »ó¼¼ dao
 * 
 * @author kim21017
 * @version $Id: MaAppLineDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 */
public interface MaAppLineDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaAppLineDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maAppLineCommonDTO
     * @return
     */
    public MaAppLineDetailDTO findDetail(MaAppLineCommonDTO maAppLineCommonDTO , User user);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaAppLineDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maAppLineDetailDTO
     * @return
     */
    public int insertDetail(MaAppLineDetailDTO maAppLineDetailDTO, User user);
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaAppLineDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maAppLineDetailDTO
     * @return
     */
    public int updateDetail(MaAppLineDetailDTO maAppLineDetailDTO, User user);
    /**
     * detail confirm
     * @author kim21017
     * @version $Id: MaAppLineDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maAppLineDetailDTO
     * @return
     */
    public int confirmDetail(MaAppLineDetailDTO maAppLineDetailDTO, User user);
}