package dream.work.cal.pminsappr.service;

import java.util.List;

import common.bean.User;
import dream.work.cal.pminsappr.dto.WorkCalPmInsApprCommonDTO;

/**
 * 예방점검계획승인 - 목록 service
 * @author  kim21017
 * @version $Id$
 * @since   1.0
 */
public interface WorkCalPmInsApprListService
{     
    public List findList(WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO,User user);
    public int deleteList(String[] deleteRows, User user) throws Exception;
    public String findTotalCount(WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO,User user);
}
