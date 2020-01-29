package dream.work.cal.unitedmonth.service;

import java.util.List;

import common.bean.User;
import dream.work.cal.unitedmonth.dto.WorkCalUnitedMonthCommonDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * 월간작업일정 - 목록 service
 * @author  youngjoo38
 * @version $Id: WorkCalUnitedMonthListService.java,v 1.0 2015/12/02 09:12:40 youngjoo38 Exp $
 * @since   1.0
 */
public interface WorkCalUnitedMonthListService
{     
    /**
     *  grid find
     * @author  youngjoo38
     * @version $Id: WorkCalUnitedMonthListService.java,v 1.0 2015/12/02 09:12:40 youngjoo38 Exp $
     * @param workCalUnitedMonthCommonDTO
     * @param maWoResultMstrCommonDTO 
     * @param 
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findWorkSchedList(WorkCalUnitedMonthCommonDTO workCalUnitedMonthCommonDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User loginUser );    
    public List findPmiSchedList(WorkCalUnitedMonthCommonDTO workCalUnitedMonthCommonDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User loginUser );    
    
    /**
     *  find schedule
     * @author  youngjoo38
     * @version $Id: WorkCalUnitedMonthListService.java,v 1.0 2015/12/02 09:12:40 youngjoo38 Exp $
     * @param workCalUnitedMonthCommonDTO
     * @param maWoResultMstrCommonDTO 
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public String findSchedule(WorkCalUnitedMonthCommonDTO workCalUnitedMonthCommonDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user) throws Exception;
    
    public String findTotalCount(WorkCalUnitedMonthCommonDTO workCalUnitedMonthCommonDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user) throws Exception;
    public String findPmiTotalCount(WorkCalUnitedMonthCommonDTO workCalUnitedMonthCommonDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user) throws Exception;

}
