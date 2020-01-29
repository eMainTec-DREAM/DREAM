package dream.work.rpt.maeqbm.dao;

import java.util.List;

import common.bean.User;
import dream.work.rpt.maeqbm.dto.MaEqBmListDTO;

/**
 * 설비고장내역DAO
 * @author  kim21017
 * @version $Id: MaEqBmListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaEqBmListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaEqBmListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqBmListDTO
     * @return List
     */
    public List findEqBmList(MaEqBmListDTO maEqBmListDTO, User user);

    public String findTotalCount(MaEqBmListDTO maEqBmListDTO, User user);

}