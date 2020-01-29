package dream.work.cal.pminsappr.dao;

import java.util.List;

import common.bean.User;
import dream.work.cal.pminsappr.dto.WorkCalPmInsApprCommonDTO;

/**
 * �������˰�ȹ���� - ��� dao
 * @author  kim21017
 * @version $Id$
 * @since   1.0
 */
public interface WorkCalPmInsApprListDAO
{
    public List findList(WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO, User user);
    public int deleteList(String id, User user);
    public String findTotalCount(WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO,User user);
}