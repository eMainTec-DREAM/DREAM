package dream.rcm.system.dao;

import common.bean.User;

import dream.rcm.system.dto.RcmSysEqDetailDTO;
import dream.rcm.system.dto.RcmSysCommonDTO;

/**
 * 설비설정 상세 dao
 * @author  kim21017
 * @version $Id: RcmSysEqDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface RcmSysEqDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: RcmSysEqDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param pmId
     * @param pmEquipId
     * @param compNo
     * @return
     */
    public RcmSysEqDetailDTO findDetail(String rcmListId, String rcmEqId, User user);

    /**
     * detail update
     * @author kim21017
     * @version $Id: RcmSysEqDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysEqDetailDTO
     * @param maPmMstrMstrCommonDTO
     * @return
     */
    public int updateDetail(RcmSysEqDetailDTO rcmSysEqDetailDTO, RcmSysCommonDTO maPmMstrCommonDTO);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: RcmSysEqDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysEqDetailDTO
     * @param maPmMstrCommonDTO
     * @return
     */
    public int insertDetail(RcmSysEqDetailDTO rcmSysEqDetailDTO, RcmSysCommonDTO maPmMstrCommonDTO);
}