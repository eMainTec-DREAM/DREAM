package dream.work.rpt.selfvendor.service.spring;

import java.util.List;

import common.bean.User;
import dream.work.rpt.selfvendor.dao.WorkRptSelfVendorListDAO;
import dream.work.rpt.selfvendor.dto.WorkRptSelfVendorCommonDTO;
import dream.work.rpt.selfvendor.service.WorkRptSelfVendorListService;

/**
 * 사내, 외주 작업 현황 Report - List Service implements
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="workRptSelfVendorListServiceTarget"
 * @spring.txbn id="workRptSelfVendorListService"
 * @spring.property name="workRptSelfVendorListDAO" ref="workRptSelfVendorListDAO"
 */
public class WorkRptSelfVendorListServiceImpl implements WorkRptSelfVendorListService
{
    private WorkRptSelfVendorListDAO workRptSelfVendorListDAO = null;

    public List findList(WorkRptSelfVendorCommonDTO workRptSelfVendorCommonDTO, User user) throws Exception
    {      
        return workRptSelfVendorListDAO.findList(workRptSelfVendorCommonDTO,user);
    }

    public WorkRptSelfVendorListDAO getWorkRptSelfVendorListDAO() {
        return workRptSelfVendorListDAO;
    }

    public void setWorkRptSelfVendorListDAO(WorkRptSelfVendorListDAO workRptSelfVendorListDAO) {
        this.workRptSelfVendorListDAO = workRptSelfVendorListDAO;
    }    
    
    public String findTotalCount(WorkRptSelfVendorCommonDTO workRptSelfVendorCommonDTO,User user)  throws Exception
    {
        return workRptSelfVendorListDAO.findTotalCount(workRptSelfVendorCommonDTO, user);
    }
}
