package dream.work.rpt.woendrate.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.rpt.woendrate.dao.WorkRptWoEndDetailListDAO;
import dream.work.rpt.woendrate.dto.WorkRptWoEndDetailListDTO;
import dream.work.rpt.woendrate.service.WorkRptWoEndDetailListService;

/**
 * 작업오더 일마감 처리율 목록 - List Service implements
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="workRptWoEndDetailListServiceTarget"
 * @spring.txbn id="workRptWoEndDetailListService"
 * @spring.property name="workRptWoEndDetailListDAO" ref="workRptWoEndDetailListDAO"
 */
public class WorkRptWoEndDetailListServiceImpl implements WorkRptWoEndDetailListService
{
    private WorkRptWoEndDetailListDAO workRptWoEndDetailListDAO = null;

    public List findList(WorkRptWoEndDetailListDTO workRptWoEndDetailListDTO, User user) throws Exception
    {      
        return workRptWoEndDetailListDAO.findList(workRptWoEndDetailListDTO,user);
    }

    public WorkRptWoEndDetailListDAO getWorkRptWoEndDetailListDAO() {
        return workRptWoEndDetailListDAO;
    }

    public void setWorkRptWoEndDetailListDAO(WorkRptWoEndDetailListDAO workRptWoEndDetailListDAO) {
        this.workRptWoEndDetailListDAO = workRptWoEndDetailListDAO;
    }    
    
    public String findTotalCount(WorkRptWoEndDetailListDTO workRptWoEndDetailListDTO,User user)  throws Exception
    {
        return workRptWoEndDetailListDAO.findTotalCount(workRptWoEndDetailListDTO, user);
    }
}
