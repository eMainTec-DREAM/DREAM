package dream.work.cal.pminsappr.service;

import java.util.List;

import common.bean.User;
import dream.work.cal.pminsappr.dto.WorkCalPmInsApprCommonDTO;
import dream.work.cal.pminsappr.dto.WorkCalPmInsApprDetailDTO;

/**
 * �������˰�ȹ���� - �����۾� ��� service
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 */
public interface WorkCalPmInsApprRsltListService
{     
    public List findList(WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO,WorkCalPmInsApprDetailDTO workCalPmInsApprDetailDTO, User user);
    public String findTotalCount(WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO,WorkCalPmInsApprDetailDTO workCalPmInsApprDetailDTO,User user);
}
