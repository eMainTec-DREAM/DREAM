package dream.rcm.system.dao;

import dream.rcm.system.dto.RcmSysEmpDetailDTO;
import dream.rcm.system.dto.RcmSysCommonDTO;

/**
 * 분석자 상세 dao
 * @author  kim21017
 * @version $Id: RcmSysEmpDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface RcmSysEmpDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: RcmSysEmpDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param pmId
     * @param pmEquipId
     * @param compNo
     * @return
     */
    public RcmSysEmpDetailDTO findDetail(String rcmListId, String rcmEmpId, String compNo);

    /**
     * detail update
     * @author kim21017
     * @version $Id: RcmSysEmpDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysEmpDetailDTO
     * @param maPmMstrMstrCommonDTO
     * @return
     */
    public int updateDetail(RcmSysEmpDetailDTO rcmSysEmpDetailDTO, RcmSysCommonDTO maPmMstrCommonDTO);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: RcmSysEmpDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysEmpDetailDTO
     * @param maPmMstrCommonDTO
     * @return
     */
    public int insertDetail(RcmSysEmpDetailDTO rcmSysEmpDetailDTO, RcmSysCommonDTO maPmMstrCommonDTO);
}