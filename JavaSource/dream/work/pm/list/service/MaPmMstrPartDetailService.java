package dream.work.pm.list.service;

import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.MaPmMstrPartDetailDTO;

/**
 * 자재 상세
 * @author  kim210117
 * @version $Id: MaPmMstrPartDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface MaPmMstrPartDetailService
{    
    /**
     * detail find
     * @author jung7126
     * @version $Id: MaPmMstrPartDetailService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
     * @since   1.0
     * 
     * @param pmId
     * @param pmPartId
     * @param compNo
     * @return
     * @throws Exception
     */
    public MaPmMstrPartDetailDTO findDetail(String pmId, String pmPartId,String compNo)throws Exception;
    /**
     * detail update
     * @author jung7126
     * @version $Id: MaPmMstrPartDetailService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
     * @since   1.0
     * 
     * @param maPmMstrPartDetailDTO
     * @param maPmMstrMstrCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaPmMstrPartDetailDTO maPmMstrPartDetailDTO, MaPmMstrCommonDTO maPmMstrMstrCommonDTO) throws Exception;
    /**
     * detail insert
     * @author jung7126
     * @version $Id: MaPmMstrPartDetailService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
     * @since   1.0
     * 
     * @param maPmMstrPartDetailDTO
     * @param maPmMstrMstrCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaPmMstrPartDetailDTO maPmMstrPartDetailDTO, MaPmMstrCommonDTO maPmMstrMstrCommonDTO) throws Exception;
}
