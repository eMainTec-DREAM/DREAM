package dream.asset.list.dao;


import common.bean.User;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrSpecDetailDTO;
import dream.asset.list.dto.MaEqMstrSpecListDTO;

/**
 * 설비제원(스펙) 상세 dao
 * @author  kim21017
 * @version $Id: MaEqMstrSpecDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface MaEqMstrSpecDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaEqMstrSpecDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param equipId
     * @param eqSpecId
     * @param compNo
     * @return
     */
    public MaEqMstrSpecDetailDTO findDetail(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrSpecListDTO maEqMstrSpecListDTO, User user);
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaEqMstrSpecDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrSpecDetailDTO
     * @param maEqMstrCommonDTO
     * @return
     */
    public int updateDetail(MaEqMstrSpecDetailDTO maEqMstrSpecDetailDTO, MaEqMstrCommonDTO maEqMstrCommonDTO, User user);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaEqMstrSpecDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrSpecDetailDTO
     * @param maEqMstrCommonDTO
     * @return
     */
    public int insertDetail(MaEqMstrSpecDetailDTO maEqMstrSpecDetailDTO, MaEqMstrCommonDTO maEqMstrCommonDTO, User user);

    public String copyDetail(String oldEquipId, String newEquipId, String oldKeyId, String newKeyId, User user);
}