package dream.work.rpt.selfvendor.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.selfvendor.dto.WorkRptSelfVendorCommonDTO;

/**
 * 사내, 외주 작업 현황 Report - List Service
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 */
public interface WorkRptSelfVendorListService
{
    /**
     * FIND LIST
     * @param workRptSelfVendorCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public List findList(WorkRptSelfVendorCommonDTO workRptSelfVendorCommonDTO, User user) throws Exception;
    
    /**
     * find Total Count
     * @author  js.lee
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptSelfVendorCommonDTO
     * @return
     */
    public String findTotalCount(WorkRptSelfVendorCommonDTO workRptSelfVendorCommonDTO, User user) throws Exception;
}
