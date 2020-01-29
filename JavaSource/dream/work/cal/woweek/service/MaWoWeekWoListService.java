package dream.work.cal.woweek.service;

import java.io.IOException;
import java.util.List;

import common.bean.User;
import dream.work.cal.woweek.dto.MaWoWeekWoCommonDTO;

/**
 * 주간작업일정 - 목록 service
 * @author  kim21017
 * @version $Id: MaWoWeekWoListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaWoWeekWoListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaWoWeekWoListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maWoWeekWoCommonDTO
     * @param 
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findSchedList(MaWoWeekWoCommonDTO maWoWeekWoCommonDTO, User user);    
    
    public String findTotalCount(MaWoWeekWoCommonDTO maWoWeekWoCommonDTO, User user);
    /**
     * delete
     * @author kim21017
     * @version $Id: MaWoWeekWoListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @param compNo
     * @return
     * @throws Exception
     */
    public int updateDeleteTagSched(String[] deleteRows, User user) throws Exception;
    
    /**
     *  find schedule
     * @author  kim21017
     * @version $Id: MaWoWeekWoListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maWoWeekWoCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public String[][] findSchedule(MaWoWeekWoCommonDTO maWoWeekWoCommonDTO, User user) throws IOException;
    public String[][] findWoType(MaWoWeekWoCommonDTO maWoWeekWoCommonDTO, User user) throws IOException;
    /**
     * report
     * @author kim21017
     * @version $Id: MaWoWeekWoListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoWeekWoCommonDTO
     * @return
     */
    public List getReportView(MaWoWeekWoCommonDTO maWoWeekWoCommonDTO);
    
    
}
