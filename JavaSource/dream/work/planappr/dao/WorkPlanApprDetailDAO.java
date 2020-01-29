package dream.work.planappr.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.pers.appreq.dto.AppReqDetailDTO;
import dream.work.planappr.dto.WorkPlanApprCommonDTO;
import dream.work.planappr.dto.WorkPlanApprDetailDTO;

/**
 * ÀÛ¾÷°èÈ¹½ÂÀÎ - »ó¼¼ dao
 * 
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 */
public interface WorkPlanApprDetailDAO extends BaseJdbcDaoSupportIntf
{
    public WorkPlanApprDetailDTO findDetail(WorkPlanApprCommonDTO workPlanApprCommonDTO,User user);
    public int insertDetail(WorkPlanApprDetailDTO workPlanApprDetailDTO, WorkPlanApprCommonDTO workPlanApprCommonDTO, User user);
    public int updateDetail(WorkPlanApprDetailDTO workPlanApprDetailDTO, WorkPlanApprCommonDTO workPlanApprCommonDTO, User user);
    public int updateStatus(WorkPlanApprDetailDTO workPlanApprDetailDTO, User user);
    public String checkAppr(WorkPlanApprDetailDTO workPlanApprDetailDTO,User user);
    public int setStatus(AppReqDetailDTO appReqDetailDTO, User user);
}