package dream.req.rpt.prewoplanrate.dao;

import java.util.List;

import common.bean.User;
import dream.req.rpt.prewoplanrate.dto.ReqRptPreWoPlanDetailListDTO;

/**
 * �۾����� ���� ��ȹ ������ ��� - List DAO
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 *
 */
public interface ReqRptPreWoPlanDetailListDAO
{
    /**
     * FIND LIST
     * @param reqRptPreWoPlanDetailListDTO
     * @param user
     * @return
     * @throws Exception
     */
    
    public List findList(ReqRptPreWoPlanDetailListDTO reqRptPreWoPlanDetailListDTO, User user) throws Exception;

    /**
     * FIND TOTAL LIST
     * @param reqRptPreWoPlanDetailListDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(ReqRptPreWoPlanDetailListDTO reqRptPreWoPlanDetailListDTO, User user) throws Exception;
    
}
