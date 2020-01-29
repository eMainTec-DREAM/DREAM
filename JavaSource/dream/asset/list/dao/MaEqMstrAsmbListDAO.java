package dream.asset.list.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.asset.list.dto.MaEqMstrAsmbListDTO;
import dream.asset.list.dto.MaEqMstrCommonDTO;

/**
 * 설비부위 목록 dao
 * @author  kim21017
 * @version $Id: MaEqMstrAsmbListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 *@since   1.0
 */
public interface MaEqMstrAsmbListDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaEqMstrAsmbListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrCommonDTO
     * @param maEqMstrAsmbListDTO
     * @param loginUser
     * @return List
     */
    public List findAsmbList(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrAsmbListDTO maEqMstrAsmbListDTO, User loginUser);

    /**
     * delete
     * @author kim21017
     * @version $Id: MaEqMstrAsmbListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteAsmbList(String id, String compNo);
}