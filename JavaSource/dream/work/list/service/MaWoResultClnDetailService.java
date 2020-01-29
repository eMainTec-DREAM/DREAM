package dream.work.list.service;

import dream.work.list.dto.MaWoResultClnDetailDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * 작업결과 작업설비 상세
 * @author  kim210117
 * @version $Id: MaWoResultClnDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface MaWoResultClnDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaWoResultClnDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param wkOrId
     * @param woClnId
     * @param compNo
     * @return
     * @throws Exception
     */
    public MaWoResultClnDetailDTO findDetail(String wkOrId, String woEquipId,String compNo)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaWoResultClnDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultClnDetailDTO
     * @param maWoResultMstrCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaWoResultClnDetailDTO maWoResultClnDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaWoResultClnDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultClnDetailDTO
     * @param maWoResultMstrCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaWoResultClnDetailDTO maWoResultClnDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) throws Exception;
}
