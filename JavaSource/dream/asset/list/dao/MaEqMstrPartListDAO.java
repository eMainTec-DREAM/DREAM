package dream.asset.list.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrPartListDTO;

/**
 * 구성자재 목록 dao
 * @author  kim21017
 * @version $Id: MaEqMstrPartListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaEqMstrPartListDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaEqMstrPartListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrCommonDTO
     * @param maEqMstrPartListDTO
     * @param loginUser
     * @return List
     */
    public List findPartList(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPartListDTO maEqMstrPartListDTO, User loginUser);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaEqMstrPartListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deletePartList(String id, String compNo);
    /**
     * FIND TOTAL LIST
     * @param maEqMstrCommonDTO
     * @param maEqMstrPartListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPartListDTO maEqMstrPartListDTO, User user) throws Exception;
    
    public String validPart(String equipId, String partId, User user) throws Exception;
}