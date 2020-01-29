package dream.work.cal.unitedmonth.dao;

import java.util.List;

import common.bean.User;
import dream.work.cal.unitedmonth.dto.WorkCalUnitedMonthCommonDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * 월간작업일정 - 목록 dao
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface WorkCalUnitedMonthListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param workCalUnitedMonthCommonDTO
     * @param maWoResultMstrCommonDTO 
     * @return List
     */
    public List findWorkSchedList(WorkCalUnitedMonthCommonDTO workCalUnitedMonthCommonDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User loginUser);
    public List findPmiSchedList(WorkCalUnitedMonthCommonDTO workCalUnitedMonthCommonDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User loginUser);
    
    public String findTotalCount(WorkCalUnitedMonthCommonDTO workCalUnitedMonthCommonDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user) throws Exception;
    
    public String findPmiTotalCount(WorkCalUnitedMonthCommonDTO workCalUnitedMonthCommonDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user) throws Exception;
    
}