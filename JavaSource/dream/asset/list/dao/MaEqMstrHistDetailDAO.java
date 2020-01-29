package dream.asset.list.dao;

import common.bean.User;
import dream.asset.list.dto.MaEqMstrHistDetailDTO;

/**
 * 설비변경이력 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: MaEqMstrHistDetailDAO.java,v 1.0 2015/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 */
public interface MaEqMstrHistDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaEqMstrHistDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param equipId
     * @param compNo
     * @return
     */
    public MaEqMstrHistDetailDTO findDetail(String eqalthist_id, User user);
}