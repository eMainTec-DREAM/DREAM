package dream.work.planappr.dao;

import java.util.List;

import common.bean.User;
import dream.work.planappr.dto.WorkPlanApprCommonDTO;

/**
 * ÀÛ¾÷°èÈ¹½ÂÀÎ - ¸ñ·Ï dao
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public interface WorkPlanApprListDAO
{
    public List findList(WorkPlanApprCommonDTO workPlanApprCommonDTO, User user);
    public int deleteList(String id, User user);
    public String findTotalCount(WorkPlanApprCommonDTO workPlanApprCommonDTO,User user);
}