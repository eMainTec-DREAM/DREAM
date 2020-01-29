package dream.work.cal.pminsappr.dao;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.pers.appreq.dto.AppReqDetailDTO;
import dream.work.cal.pminsappr.dto.WorkCalPmInsApprCommonDTO;
import dream.work.cal.pminsappr.dto.WorkCalPmInsApprDetailDTO;

/**
 * øππÊ¡°∞À∞Ë»πΩ¬¿Œ - ªÛºº dao
 * 
 * @author kim21017
 * @version $Id$
 * @since 1.0
 */
public interface WorkCalPmInsApprDetailDAO extends BaseJdbcDaoSupportIntf
{
    public WorkCalPmInsApprDetailDTO findDetail(WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO,User user);
    public int insertDetail(WorkCalPmInsApprDetailDTO workCalPmInsApprDetailDTO, WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO, User user);
    public int updateDetail(WorkCalPmInsApprDetailDTO workCalPmInsApprDetailDTO, WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO, User user);
    public int updateStatus(WorkCalPmInsApprDetailDTO workCalPmInsApprDetailDTO, User user);
    public String checkAppr(WorkCalPmInsApprDetailDTO workCalPmInsApprDetailDTO,User user);
    public int setStatus(AppReqDetailDTO appReqDetailDTO, User user);
}