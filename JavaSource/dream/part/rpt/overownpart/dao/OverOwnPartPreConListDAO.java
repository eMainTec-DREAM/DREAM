package dream.part.rpt.overownpart.dao;

import java.util.List;

import common.bean.User;
import dream.part.rpt.overownpart.dto.OverOwnPartPreConCommonDTO;

/**
 * OverOwnPartPreCon Page - List DAO
 * @author youngjoo38
 * @version $Id: OverOwnPartPreConListDAO.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 *
 */
public interface OverOwnPartPreConListDAO
{
    /**
     * FIND LIST
     * @param overOwnPartPreConCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    
    public List findList(OverOwnPartPreConCommonDTO overOwnPartPreConCommonDTO, User user) throws Exception;

    /**
     * FIND TOTAL LIST
     * @param overOwnPartPreConCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String findTotalCount(OverOwnPartPreConCommonDTO overOwnPartPreConCommonDTO, User user) throws Exception;
    
}
