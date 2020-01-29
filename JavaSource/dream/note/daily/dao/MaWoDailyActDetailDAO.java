package dream.note.daily.dao;

import dream.note.daily.dto.MaWoDailyActDetailDTO;
import dream.note.daily.dto.MaWoDailyCommonDTO;

/**
 * 일일작업 - Main Activities 상세 dao
 * @author  kim21017
 * @version $Id: MaWoDailyActDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface MaWoDailyActDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaWoDailyActDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param woDayListId
     * @param woDayActId
     * @param compNo
     * @return
     */
    public MaWoDailyActDetailDTO findDetail(String woDayListId, String woDayActId, String compNo);

    /**
     * detail update
     * @author kim21017
     * @version $Id: MaWoDailyActDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoDailyActDetailDTO
     * @param maWoDailyCommonDTO
     * @return
     */
    public int updateDetail(MaWoDailyActDetailDTO maWoDailyActDetailDTO, MaWoDailyCommonDTO maWoDailyCommonDTO);
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaWoDailyActDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoDailyActDetailDTO
     * @param maWoDailyCommonDTO
     * @return
     */
    public int insertDetail(MaWoDailyActDetailDTO maWoDailyActDetailDTO, MaWoDailyCommonDTO maWoDailyCommonDTO);
}