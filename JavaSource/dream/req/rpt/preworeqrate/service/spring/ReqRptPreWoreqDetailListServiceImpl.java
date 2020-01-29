package dream.req.rpt.preworeqrate.service.spring;

import java.util.List;

import common.bean.User;
import dream.req.rpt.preworeqrate.dao.ReqRptPreWoreqDetailListDAO;
import dream.req.rpt.preworeqrate.dto.ReqRptPreWoreqDetailListDTO;
import dream.req.rpt.preworeqrate.service.ReqRptPreWoreqDetailListService;

/**
 * 작업의뢰 시스템 요청 목록 - List Service implements
 * @author nhkim8548
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="reqRptPreWoreqDetailListServiceTarget"
 * @spring.txbn id="reqRptPreWoreqDetailListService"
 * @spring.property name="reqRptPreWoreqDetailListDAO" ref="reqRptPreWoreqDetailListDAO"
 */
public class ReqRptPreWoreqDetailListServiceImpl implements ReqRptPreWoreqDetailListService
{
    private ReqRptPreWoreqDetailListDAO reqRptPreWoreqDetailListDAO = null;

    public List findList(ReqRptPreWoreqDetailListDTO reqRptPreWoreqDetailListDTO, User user) throws Exception
    {      
        return reqRptPreWoreqDetailListDAO.findList(reqRptPreWoreqDetailListDTO, user);
    }

    public ReqRptPreWoreqDetailListDAO getReqRptPreWoreqDetailListDAO() {
        return reqRptPreWoreqDetailListDAO;
    }

    public void setReqRptPreWoreqDetailListDAO(ReqRptPreWoreqDetailListDAO reqRptPreWoreqDetailListDAO) {
        this.reqRptPreWoreqDetailListDAO = reqRptPreWoreqDetailListDAO;
    }    
    
    public String findTotalCount(ReqRptPreWoreqDetailListDTO reqRptPreWoreqDetailListDTO,User user)  throws Exception
    {
        return reqRptPreWoreqDetailListDAO.findTotalCount(reqRptPreWoreqDetailListDTO, user);
    }
}
