package dream.note.daily.service;

import java.util.List;

import common.bean.User;
import dream.note.daily.dto.MaWoDailyCommonDTO;
import dream.note.daily.dto.MaWoDailyDetailDTO;
import dream.pers.appreq.dto.AppReqDetailDTO;

/**
 *  - »ó¼¼ service
 * 
 * @author kim21017
 * @version $Id:$
 * @since 1.0
 */
public interface MaWoDailyDetailService
{    
    /**
     * detail find
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param ptRecListId
     * @return
     * @throws Exception
     */
    public MaWoDailyDetailDTO findDetail(MaWoDailyCommonDTO maWoDailyCommonDTO, User loginUser)throws Exception;
   
    
    /**
     * detail update
     * @author kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maWoDailyDetailDTO
     * @param user 
     * @return
     * @throws Exception
     */
    public int updateDetail(MaWoDailyDetailDTO maWoDailyDetailDTO, User user) throws Exception;

    public void appProcess(AppReqDetailDTO appReqDetailDTO, User user);

    public List getReportView(MaWoDailyDetailDTO maWoDailyDetailDTO, User user);

    public int insertDetail(MaWoDailyDetailDTO maWoDailyDetailDTO, User user) throws Exception;

    public int updateStatus(MaWoDailyDetailDTO maWoDailyDetailDTO, User user) throws Exception;

}
