package dream.asset.list.dao;

import common.bean.User;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrPartDetailDTO;
import dream.asset.list.dto.MaEqMstrPartListDTO;

/**
 * 구성자재 상세 dao
 * @author  kim21017
 * @version $Id: MaEqMstrPartDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface MaEqMstrPartDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaEqMstrPartDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrPartDetailDTO
     * @param user
     * @return
     */
    public MaEqMstrPartDetailDTO findDetail(MaEqMstrPartListDTO maEqMstrPartListDTO, User user);
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaEqMstrPartDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrPartDetailDTO
     * @param maEqMstrCommonDTO
     * @param user
     * @return
     */
    public int updateDetail(MaEqMstrPartDetailDTO maEqMstrPartDetailDTO, MaEqMstrCommonDTO maEqMstrCommonDTO, User user);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaEqMstrPartDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrPartDetailDTO
     * @param maEqMstrCommonDTO
     * @param user
     * @return
     */
    public int insertDetail(MaEqMstrPartDetailDTO maEqMstrPartDetailDTO, MaEqMstrCommonDTO maEqMstrCommonDTO, User user);
    
    public String copyDetail(String oldEquipId, String newEquipId, String oldKeyId, String newKeyId, User user);
    
}