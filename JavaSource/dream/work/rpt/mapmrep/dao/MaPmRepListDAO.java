package dream.work.rpt.mapmrep.dao;

import java.util.List;

import common.bean.User;
import dream.work.rpt.mapmrep.dto.MaPmRepListDTO;

/**
 * 예방수리내역DAO
 * @author  kim21017
 * @version $Id: MaPmRepListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaPmRepListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaPmRepListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPmRepListDTO
     * @return List
     */
    public List findPmRepList(MaPmRepListDTO maPmRepListDTO, User user);

    public String findTotalCount(MaPmRepListDTO maPmRepListDTO,User user);

}