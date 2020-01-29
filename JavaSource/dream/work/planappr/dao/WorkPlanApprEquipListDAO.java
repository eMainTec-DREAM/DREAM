package dream.work.planappr.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.work.planappr.dto.WorkPlanApprCommonDTO;
import dream.work.planappr.dto.WorkPlanApprDetailDTO;

/**
 * 작업계획승인 - 점검작업 목록 dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface WorkPlanApprEquipListDAO extends BaseJdbcDaoSupportIntf
{
    public List findList(WorkPlanApprCommonDTO workPlanApprCommonDTO,WorkPlanApprDetailDTO workPlanApprDetailDTO, User user);
    public String findTotalCount(WorkPlanApprCommonDTO workPlanApprCommonDTO,WorkPlanApprDetailDTO workPlanApprDetailDTO, User user);
    public void deleteWoPlanApprList( WorkPlanApprCommonDTO workPlanApprCommonDTO, WorkPlanApprDetailDTO workPlanApprDetailDTO, User user);
    public void insertWoPlanApprList( WorkPlanApprCommonDTO workPlanApprCommonDTO, WorkPlanApprDetailDTO workPlanApprDetailDTO, User user);
}