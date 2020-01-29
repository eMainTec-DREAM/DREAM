package dream.work.rpt.pmi.point.value.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.rpt.pmi.point.value.dao.WorkRptPmiPointValueListDAO;
import dream.work.rpt.pmi.point.value.dto.WorkRptPmiPointValueCommonDTO;
import dream.work.rpt.pmi.point.value.service.WorkRptPmiPointValueListService;

/**
 * 정기점검항목결과 - 목록 ServiceImpl
 * @author nhkim8548
 * @version $Id: WorkRptPmiPointValueListServiceImpl.java, v1.0 2019/07/10 10:39:51 nhkim8548 Exp $
 * @since 1.0
 * 
 * @spring.bean id="workRptPmiPointValueListServiceTarget"
 * @spring.txbn id="workRptPmiPointValueListService"
 * @spring.property name="workRptPmiPointValueListDAO" ref="workRptPmiPointValueListDAO"
 */
public class WorkRptPmiPointValueListServiceImpl implements WorkRptPmiPointValueListService
{
    private WorkRptPmiPointValueListDAO workRptPmiPointValueListDAO = null;

    public WorkRptPmiPointValueListDAO getWorkRptPmiPointValueListDAO() {
        return workRptPmiPointValueListDAO;
    }
    
    public void setWorkRptPmiPointValueListDAO(WorkRptPmiPointValueListDAO workRptPmiPointValueListDAO) {
        this.workRptPmiPointValueListDAO = workRptPmiPointValueListDAO;
    }

    public List findPointValueList(WorkRptPmiPointValueCommonDTO workRptPmiPointValueLCommonDTO, User user) throws Exception
    {      
        List result = workRptPmiPointValueListDAO.findPointValueList(workRptPmiPointValueLCommonDTO, user);
 
        return result;
    }
    
    public String findTotalCount(WorkRptPmiPointValueCommonDTO workRptPmiPointValueCommonDTO, User user) throws Exception 
    {
        String cnt = workRptPmiPointValueListDAO.findTotalCount(workRptPmiPointValueCommonDTO, user);
        
        return cnt;
    }
}

