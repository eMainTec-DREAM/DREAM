package dream.work.cal.womonth.service;

import java.util.List;

import common.bean.User;
import dream.work.cal.womonth.dto.MaWoMonthWoCommonDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * 월간작업일정 - 목록 service
 * @author  kim21017
 * @version $Id: MaWoMonthWoListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaWoMonthWoListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaWoMonthWoListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maWoMonthWoCommonDTO
     * @param maWoResultMstrCommonDTO 
     * @param 
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findSchedList(MaWoMonthWoCommonDTO maWoMonthWoCommonDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User loginUser );    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaWoMonthWoListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @param compNo
     * @return
     * @throws Exception
     */
    public int updateDeleteTagSched(String[] deleteRows,User user) throws Exception;
    
    /**
     *  find schedule
     * @author  kim21017
     * @version $Id: MaWoMonthWoListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maWoMonthWoCommonDTO
     * @param maWoResultMstrCommonDTO 
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public String findSchedule(MaWoMonthWoCommonDTO maWoMonthWoCommonDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user) throws Exception;
    /**
     * report
     * @author kim21017
     * @version $Id: MaWoMonthWoListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoMonthWoCommonDTO
     * @return
     */
    public List getReportView(MaWoMonthWoCommonDTO maWoMonthWoCommonDTO);
    
    public String findTotalCount(MaWoMonthWoCommonDTO maWoMonthWoCommonDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user) throws Exception;

}
