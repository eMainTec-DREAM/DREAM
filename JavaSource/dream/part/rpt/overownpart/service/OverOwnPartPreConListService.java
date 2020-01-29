package dream.part.rpt.overownpart.service;

import java.util.List;

import common.bean.User;
import dream.part.rpt.overownpart.dto.OverOwnPartPreConCommonDTO;

/**
 * OverOwnPartPreCon Page - List Service
 * @author youngjoo38
 * @version $Id: OverOwnPartPreConListService.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 */
public interface OverOwnPartPreConListService
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
     * find Total Count
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param overOwnPartPreConCommonDTO
     * @return
     */
    public String findTotalCount(OverOwnPartPreConCommonDTO overOwnPartPreConCommonDTO, User user) throws Exception;
}
