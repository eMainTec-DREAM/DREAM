package dream.work.pm.list.dao;

import dream.work.pm.list.dto.MaPmEqClnDetailDTO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;

/**
 * 사용자재 상세 dao
 * @author  kim21017
 * @version $Id: MaPmEqClnDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface MaPmEqClnDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaPmEqClnDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param pmId
     * @param pmEquipId
     * @param compNo
     * @return
     */
    public MaPmEqClnDetailDTO findDetail(String pmId, String pmEquipId, String compNo);

    /**
     * detail update
     * @author kim21017
     * @version $Id: MaPmEqClnDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPmEqClnDetailDTO
     * @param maPmMstrMstrCommonDTO
     * @return
     */
    public int updateDetail(MaPmEqClnDetailDTO maPmEqClnDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaPmEqClnDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPmEqClnDetailDTO
     * @param maPmMstrCommonDTO
     * @return
     */
    public int insertDetail(MaPmEqClnDetailDTO maPmEqClnDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO);
}