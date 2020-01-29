package dream.note.daily.service;

import dream.note.daily.dto.MaWoDailyActDetailDTO;
import dream.note.daily.dto.MaWoDailyCommonDTO;

/**
 * 일일작업 - Main Activities
 * @author  kim210117
 * @version $Id: MaWoDailyActDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface MaWoDailyActDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaWoDailyActDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param wkOrId
     * @param woCraftId
     * @param compNo
     * @return
     * @throws Exception
     */
    public MaWoDailyActDetailDTO findDetail(String woDayListId, String woDayActId,String compNo)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaWoDailyActDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoDailyActDetailDTO
     * @param maWoDailyCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaWoDailyActDetailDTO maWoDailyActDetailDTO, MaWoDailyCommonDTO maWoDailyCommonDTO) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaWoDailyActDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoDailyActDetailDTO
     * @param maWoDailyCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaWoDailyActDetailDTO maWoDailyActDetailDTO, MaWoDailyCommonDTO maWoDailyCommonDTO) throws Exception;
}
