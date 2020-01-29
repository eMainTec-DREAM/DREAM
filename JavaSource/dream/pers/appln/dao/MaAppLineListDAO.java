package dream.pers.appln.dao;

import java.util.List;

import common.bean.User;
import dream.pers.appln.dto.MaAppLineCommonDTO;

/**
 * dao
 * @author  kim21017
 * @version $Id: MaAppLineListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaAppLineListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaAppLineListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maAppLineCommonDTO
     * @return List
     */
    public List findQnaList(MaAppLineCommonDTO maAppLineCommonDTO, User user);

    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaAppLineListDAO.java,v 1.0 2015   5/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @return
     */
    public int deleteQna(String id, User user);

    public int insertLine(MaAppLineCommonDTO maAppLineCommonDTO, User user);


    public int deleteLine(MaAppLineCommonDTO maAppLineCommonDTO, User user);
    public int mergeAppList(MaAppLineCommonDTO maAppLineCommonDTO, User user);
    
    public String findTotalCount(MaAppLineCommonDTO maAppLineCommonDTO, User user);

}