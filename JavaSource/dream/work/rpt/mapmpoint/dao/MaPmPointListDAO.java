package dream.work.rpt.mapmpoint.dao;

import java.util.List;

import common.bean.User;
import dream.work.rpt.mapmpoint.dto.MaPmPointListDTO;

/**
 * 예방점검내역DAO
 * @author  kim21017
 * @version $Id: MaPmPointListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaPmPointListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaPmPointListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPmPointListDTO
     * @return List
     */
    public List findPmPointList(MaPmPointListDTO maPmPointListDTO, User user);
    
    public String findTotalCount(MaPmPointListDTO maPmPointListDTO, User user);

}