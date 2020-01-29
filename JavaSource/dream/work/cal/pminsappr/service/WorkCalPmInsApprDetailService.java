package dream.work.cal.pminsappr.service;

import common.bean.User;
import dream.pers.appreq.dto.AppReqDetailDTO;
import dream.work.cal.pminsappr.dto.WorkCalPmInsApprCommonDTO;
import dream.work.cal.pminsappr.dto.WorkCalPmInsApprDetailDTO;

/**
 * øππÊ¡°∞À∞Ë»πΩ¬¿Œ - ªÛºº service
 * @author kim21017
 * @version $Id$
 * @since 1.0
 */
public interface WorkCalPmInsApprDetailService 
{    
    public WorkCalPmInsApprDetailDTO findDetail(WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO, User user)throws Exception;
    public int insertDetail(WorkCalPmInsApprDetailDTO workCalPmInsApprDetailDTO, WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO, User user) throws Exception;
    public int updateDetail(WorkCalPmInsApprDetailDTO workCalPmInsApprDetailDTO, WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO, User user) throws Exception;
    public int updateStatus(WorkCalPmInsApprDetailDTO workCalPmInsApprDetailDTO, User user) throws Exception;
    public String checkAppr(WorkCalPmInsApprDetailDTO workCalPmInsApprDetailDTO,User user) throws Exception;
    public void appProcess(AppReqDetailDTO appReqDetailDTO, User user) throws Exception;
}
