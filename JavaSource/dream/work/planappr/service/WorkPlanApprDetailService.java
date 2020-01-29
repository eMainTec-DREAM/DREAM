package dream.work.planappr.service;

import common.bean.User;
import dream.pers.appreq.dto.AppReqDetailDTO;
import dream.work.planappr.dto.WorkPlanApprCommonDTO;
import dream.work.planappr.dto.WorkPlanApprDetailDTO;

/**
 * ÀÛ¾÷°èÈ¹½ÂÀÎ - »ó¼¼ service
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 */
public interface WorkPlanApprDetailService 
{    
    public WorkPlanApprDetailDTO findDetail(WorkPlanApprCommonDTO workPlanApprCommonDTO, User user)throws Exception;
    public int insertDetail(WorkPlanApprDetailDTO workPlanApprDetailDTO, WorkPlanApprCommonDTO workPlanApprCommonDTO, User user) throws Exception;
    public int updateDetail(WorkPlanApprDetailDTO workPlanApprDetailDTO, WorkPlanApprCommonDTO workPlanApprCommonDTO,User user) throws Exception;
    public int updateStatus(WorkPlanApprDetailDTO workPlanApprDetailDTO, User user) throws Exception;
    public String checkAppr(WorkPlanApprDetailDTO workPlanApprDetailDTO,User user) throws Exception;
    public void appProcess(AppReqDetailDTO appReqDetailDTO, User user) throws Exception;
}
