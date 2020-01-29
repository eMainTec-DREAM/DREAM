package dream.work.rpt.woendrate.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.woendrate.dto.WorkRptWoEndDetailListDTO;

/**
 * 작업오더 일마감 처리율 목록 - List Service
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 */
public interface WorkRptWoEndDetailListService
{
    /**
     * FIND LIST
     * @param workRptWoEndDetailListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public List findList(WorkRptWoEndDetailListDTO workRptWoEndDetailListDTO, User user) throws Exception;
    
    /**
     * find Total Count
     * @author  js.lee
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptWoEndDetailListDTO
     * @return
     */
    public String findTotalCount(WorkRptWoEndDetailListDTO workRptWoEndDetailListDTO, User user) throws Exception;
}
