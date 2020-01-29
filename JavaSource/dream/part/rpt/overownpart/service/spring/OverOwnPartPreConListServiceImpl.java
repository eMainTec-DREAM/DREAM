package dream.part.rpt.overownpart.service.spring;

import java.util.List;

import common.bean.User;
import dream.part.rpt.overownpart.dao.OverOwnPartPreConListDAO;
import dream.part.rpt.overownpart.dto.OverOwnPartPreConCommonDTO;
import dream.part.rpt.overownpart.service.OverOwnPartPreConListService;

/**
 * OverOwnPartPreCon Page - List Service implements
 * @author youngjoo38
 * @version $Id: OverOwnPartPreConListServiceImpl.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @spring.bean id="overOwnPartPreConListServiceTarget"
 * @spring.txbn id="overOwnPartPreConListService"
 * @spring.property name="overOwnPartPreConListDAO" ref="overOwnPartPreConListDAO"
 */
public class OverOwnPartPreConListServiceImpl implements OverOwnPartPreConListService
{
    private OverOwnPartPreConListDAO overOwnPartPreConListDAO = null;

    public List findList(OverOwnPartPreConCommonDTO overOwnPartPreConCommonDTO, User user) throws Exception
    {      
        return overOwnPartPreConListDAO.findList(overOwnPartPreConCommonDTO,user);
    }

    public OverOwnPartPreConListDAO getOverOwnPartPreConListDAO() {
        return overOwnPartPreConListDAO;
    }

    public void setOverOwnPartPreConListDAO(OverOwnPartPreConListDAO overOwnPartPreConListDAO) {
        this.overOwnPartPreConListDAO = overOwnPartPreConListDAO;
    }    
    
    public String findTotalCount(OverOwnPartPreConCommonDTO overOwnPartPreConCommonDTO,User user)  throws Exception
    {
        return overOwnPartPreConListDAO.findTotalCount(overOwnPartPreConCommonDTO, user);
    }
}
