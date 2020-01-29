package dream.work.rpt.pm.month.rate.dao;

import java.util.List;

import dream.work.rpt.pm.month.rate.dto.WorkRptPmMonthRateListDTO;

/**
 * 월별점검실행율 DAO
 * @author  sy.yang
 * @version $Id: WorkRptPmMonthRateListDAO.java,v 1.0 2015/12/02 09:14:12 sy.yang Exp $
 * @since   1.0
 */
public interface WorkRptPmMonthRateListDAO
{
    /**
     * grid find
     * @author  sy.yang
     * @version $Id: WorkRptPmMonthRateListDAO.java,v 1.0 2015/12/02 09:14:12 sy.yang Exp $
     * @since   1.0
     * 
     * @param workRptPmMonthRateListDTO
     * @return List
     */
    public List findWoList(WorkRptPmMonthRateListDTO workRptPmMonthRateListDTO);
}