package dream.rcm.system.dao;

import dream.rcm.system.dto.RcmSysFDefDetailDTO;
import dream.rcm.system.dto.RcmSysCommonDTO;

/**
 * 기능정의 상세 dao
 * @author  kim21017
 * @version $Id: RcmSysFDefDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface RcmSysFDefDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: RcmSysFDefDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param pmId
     * @param pmEquipId
     * @param compNo
     * @return
     */
    public RcmSysFDefDetailDTO findDetail(String rcmListId, String rcmFuncId, String compNo);

    /**
     * detail update
     * @author kim21017
     * @version $Id: RcmSysFDefDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysFDefDetailDTO
     * @param maPmMstrMstrCommonDTO
     * @return
     */
    public int updateDetail(RcmSysFDefDetailDTO rcmSysFDefDetailDTO, RcmSysCommonDTO maPmMstrCommonDTO);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: RcmSysFDefDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysFDefDetailDTO
     * @param maPmMstrCommonDTO
     * @return
     */
    public int insertDetail(RcmSysFDefDetailDTO rcmSysFDefDetailDTO, RcmSysCommonDTO maPmMstrCommonDTO);
}