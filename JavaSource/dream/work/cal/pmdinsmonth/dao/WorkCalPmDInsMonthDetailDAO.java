package dream.work.cal.pmdinsmonth.dao;

import java.util.List;

import common.bean.User;
import dream.work.cal.pmdinsmonth.dto.WorkCalPmDInsMonthDetailDTO;

/**
 * 월간예방일정 - 상세 dao
 *
 * @author kim21017
 * @version $Id: WorkCalPmDInsMonthDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 */
public interface WorkCalPmDInsMonthDetailDAO
{
    public int[] update(List<WorkCalPmDInsMonthDetailDTO> list, User user) throws Exception;
}