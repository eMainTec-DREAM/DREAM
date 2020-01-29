package dream.work.pm.list.service;

import dream.work.pm.list.dto.MaPmEqClnDetailDTO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;

/**
 * 예방설비 상세
 * @author  kim21017
 * @version $Id: MaPmEqClnDetailService.java,v 1.0 2015/12/04 09:08:29 kim21017 Exp $
 * @since   1.0
 */
public interface MaPmEqClnDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaPmEqClnDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param pmId
     * @param pmPartId
     * @param compNo
     * @return
     * @throws Exception
     */
    public MaPmEqClnDetailDTO findDetail(String pmId, String pmEquipId,String compNo)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaPmEqClnDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPmEqClnDetailDTO
     * @param maPmMstrMstrCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaPmEqClnDetailDTO maPmEqClnDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaPmEqClnDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPmEqClnDetailDTO
     * @param maPmMstrMstrCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaPmEqClnDetailDTO maPmEqClnDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO) throws Exception;
}
