package dream.work.rpt.pm.month.rate.service;

import java.util.List;

import dream.work.rpt.pm.month.rate.dto.WorkRptPmMonthRateListDTO;

/**
 * �������˽����� service
 * @author  sy.yang
 * @version $Id: WorkRptPmMonthRateListService.java,v 1.0 2015/12/02 09:12:40 sy.yang Exp $
 * @since   1.0
 */
public interface WorkRptPmMonthRateListService
{     
    /**
     *  grid find
     * @author  sy.yang
     * @version $Id: WorkRptPmMonthRateListService.java,v 1.0 2015/12/02 09:12:40 sy.yang Exp $
     * @param workRptPmMonthRateListDTO
     * @since   1.0
     * 
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findWoList(WorkRptPmMonthRateListDTO workRptPmMonthRateListDTO);    
}
