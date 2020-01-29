package dream.asset.list.dao;

import java.util.List;

import common.bean.User;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrMoldModHistListDTO;

/**
 * 구성자재 목록 dao
 * @author  kim21017
 * @version $Id: MaEqMstrMoldModHistListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaEqMstrMoldModHistListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaEqMstrMoldModHistListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrCommonDTO
     * @param maEqMstrMoldModHistListDTO
     * @param loginUser
     * @return List
     */
    public List findMoldModHistList(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrMoldModHistListDTO maEqMstrMoldModHistListDTO, User loginUser);
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaEqMstrMoldModHistListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteMoldModHistList(String id, String compNo);
    
    /**
     * grid find total count
     * @author  kim21017
     * @version $Id: MaEqMstrMoldModHistListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrCommonDTO
     * @param maEqMstrMoldModHistListDTO
     * @param loginUser
     * @return String
     */
    public String findTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrMoldModHistListDTO maEqMstrMoldModHistListDTO, User loginUser);
    

}