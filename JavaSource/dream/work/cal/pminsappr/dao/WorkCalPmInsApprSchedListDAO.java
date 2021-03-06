package dream.work.cal.pminsappr.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.work.cal.pminsappr.dto.WorkCalPmInsApprCommonDTO;
import dream.work.cal.pminsappr.dto.WorkCalPmInsApprDetailDTO;

/**
 * 예방점검계획승인 - 점검작업 목록 dao
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 */
public interface WorkCalPmInsApprSchedListDAO extends BaseJdbcDaoSupportIntf
{
    public List findList(WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO,WorkCalPmInsApprDetailDTO workCalPmInsApprDetailDTO, User user);
    public String findTotalCount(WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO,WorkCalPmInsApprDetailDTO workCalPmInsApprDetailDTO, User user);
    public void deletePmInsSchedApprList( WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO, WorkCalPmInsApprDetailDTO workCalPmInsApprDetailDTO, User user);
    public void insertPmInsSchedApprList(  WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO,   WorkCalPmInsApprDetailDTO workCalPmInsApprDetailDTO, User user);
}