package dream.work.planappr.service;

import java.util.List;

import common.bean.User;
import dream.work.planappr.dto.WorkPlanApprCommonDTO;

/**
 * ÀÛ¾÷°èÈ¹½ÂÀÎ - ¸ñ·Ï service
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface WorkPlanApprListService
{     
    public List findList(WorkPlanApprCommonDTO workPlanApprCommonDTO,User user);
    public int deleteList(String[] deleteRows, User user) throws Exception;
    public String findTotalCount(WorkPlanApprCommonDTO workPlanApprCommonDTO,User user);
}
