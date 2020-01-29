package dream.work.rpt.woendrate.dao;

import java.util.List;

import common.bean.User;
import dream.work.rpt.woendrate.dto.WorkRptWoEndDetailListDTO;

/**
 * �۾����� �ϸ��� ó���� ��� - List DAO
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 *
 */
public interface WorkRptWoEndDetailListDAO
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
     * FIND TOTAL LIST
     * @param workRptWoEndDetailListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(WorkRptWoEndDetailListDTO workRptWoEndDetailListDTO, User user) throws Exception;
    
}
