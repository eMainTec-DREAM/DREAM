package dream.work.pm.list.dao;

import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.MaPmMstrPartDetailDTO;

/**
 * 사용자재 상세 dao
 * @author  jung7126
 * @version $Id: MaPmMstrPartDetailDAO.java,v 1.0 2015/12/04 08:10:27 jung7126 Exp $
 * @since   1.0
 */
public interface MaPmMstrPartDetailDAO
{
    /**
     * detail find
     * @author jung7126
     * @version $Id: MaPmMstrPartDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param pmId
     * @param pmPartId
     * @param compNo
     * @return
     */
    public MaPmMstrPartDetailDTO findDetail(String pmId, String pmPartId, String compNo);

    /**
     * detail update
     * @author jung7126
     * @version $Id: MaPmMstrPartDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param maPmMstrPartDetailDTO
     * @param maPmMstrMstrCommonDTO
     * @return
     */
    public int updateDetail(MaPmMstrPartDetailDTO maPmMstrPartDetailDTO, MaPmMstrCommonDTO maPmMstrMstrCommonDTO);
    
    /**
     * detail insert
     * @author jung7126
     * @version $Id: MaPmMstrPartDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param maPmMstrPartDetailDTO
     * @param maPmMstrMstrCommonDTO
     * @return
     */
    public int insertDetail(MaPmMstrPartDetailDTO maPmMstrPartDetailDTO, MaPmMstrCommonDTO maPmMstrMstrCommonDTO);
}