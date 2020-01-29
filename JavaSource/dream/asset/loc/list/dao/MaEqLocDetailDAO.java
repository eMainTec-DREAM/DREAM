package dream.asset.loc.list.dao;

import common.bean.User;
import dream.asset.loc.list.dto.MaEqLocDetailDTO;

/**
 * 설비위치 - 상세
 * 
 * @author kim21017
 * @version $Id: MaEqLocDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 */
public interface MaEqLocDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaEqLocDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param eqLocId
     * @param compNo
     * @return
     */
    public MaEqLocDetailDTO findDetail(String eqLocId, User user);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaEqLocDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqLocDetailDTO
     * @return
     */
    public int insertDetail(MaEqLocDetailDTO maEqLocDetailDTO);
    public int insertBuildingEquipment(MaEqLocDetailDTO maEqLocDetailDTO);
    
    public int insertLevelNDetail(MaEqLocDetailDTO maEqLocDetailDTO);
    public int insertLevelYDetail(MaEqLocDetailDTO maEqLocDetailDTO);
    
    public int insertFullDescDetail(MaEqLocDetailDTO maEqLocDetailDTO);
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaEqLocDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqLocDetailDTO
     * @return
     */
    public int updateDetail(MaEqLocDetailDTO maEqLocDetailDTO, User user);
    
    public int updateBuildingEquipment(MaEqLocDetailDTO maEqLocDetailDTO, User user);
    
    public int updateRunTime(MaEqLocDetailDTO maEqLocDetailDTO, User user);
    
	public String findEqLocId(String eqLocNo, User user);
}