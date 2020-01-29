package dream.work.rpt.pmi.point.value.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.pmi.point.value.dto.WorkRptPmiPointValueCommonDTO;

/**
 * 정기점검항목결과 - 목록 Service
 * @author  nhkim8548
 * @version $Id: WorkRptPmiPointValueListService.java, v1.0 2019/07/10 10:54:40 nhkim8548 Exp $
 * @since   1.0
 */
public interface WorkRptPmiPointValueListService
{     
    /**
     * grid find
     * @author  nhkim8548
     * @version $Id: WorkRptPmiPointValueListService.java, v1.0 2019/07/10 10:55:40 nhkim8548 Exp $
     * @since   1.0
     * 
     * @param   workRptPmiPointValueCommonDTO
     * @param   user
     * @throws  Exception
     */
    public List findPointValueList(WorkRptPmiPointValueCommonDTO workRptPmiPointValueCommonDTO, User user) throws Exception;

	public String findTotalCount(WorkRptPmiPointValueCommonDTO workRptPmiPointValueLCommonDTO, User user)throws Exception;
}
