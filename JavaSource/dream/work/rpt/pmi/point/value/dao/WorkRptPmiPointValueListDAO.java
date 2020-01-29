package dream.work.rpt.pmi.point.value.dao;

import java.util.List;

import common.bean.User;
import dream.work.rpt.pmi.point.value.dto.WorkRptPmiPointValueCommonDTO;

/**
 * 정기점검항목결과 - 목록 Dao
 * @author  nhkim8548
 * @version $Id: WorkRptPmiPointValueListDAO.java, v1.0 2019/07/10 10:57:12 nhkim8548 Exp $
 * @since   1.0
 */
public interface WorkRptPmiPointValueListDAO
{
    /**
     * grid find
     * @author  nhkim8548
     * @version $Id: WorkRptPmiPointValueListDAO.java, v1.0 2019/07/10 10:58:12 nhkim8548 Exp $
     * @since   1.0
     * 
     * @param   workRptPmiPointValueListDTO
     * @param   user
     * @return  List
     */
    public List findPointValueList(WorkRptPmiPointValueCommonDTO workRptPmiPointValueCommonDTO, User user) throws Exception;
    
    public String findTotalCount(WorkRptPmiPointValueCommonDTO workRptPmiPointValueCommonDTO, User user) throws Exception;
}