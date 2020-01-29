package dream.work.planappr.service;

import java.util.List;

import common.bean.User;
import dream.work.planappr.dto.WorkPlanApprCommonDTO;
import dream.work.planappr.dto.WorkPlanApprDetailDTO;

/**
 * ÀÛ¾÷°èÈ¹½ÂÀÎ - ÀÛ¾÷°èÈ¹ ¸ñ·Ï service
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface WorkPlanApprEquipListService
{     
    public List findList(WorkPlanApprCommonDTO workPlanApprCommonDTO,WorkPlanApprDetailDTO workPlanApprDetailDTO, User user);
    public String findTotalCount(WorkPlanApprCommonDTO workPlanApprCommonDTO,WorkPlanApprDetailDTO workPlanApprDetailDTO,User user)  throws Exception;
    public void insertEquip(WorkPlanApprCommonDTO workPlanApprCommonDTO,WorkPlanApprDetailDTO workPlanApprDetailDTO, User user);
}
