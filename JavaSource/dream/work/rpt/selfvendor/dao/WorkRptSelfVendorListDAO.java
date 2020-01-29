package dream.work.rpt.selfvendor.dao;

import java.util.List;

import common.bean.User;
import dream.work.rpt.selfvendor.dto.WorkRptSelfVendorCommonDTO;

/**
 * 사내, 외주 작업 현황 Report - List DAO
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 *
 */
public interface WorkRptSelfVendorListDAO
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
     * FIND TOTAL LIST
     * @param workRptSelfVendorCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(WorkRptSelfVendorCommonDTO workRptSelfVendorCommonDTO, User user) throws Exception;
    
}
