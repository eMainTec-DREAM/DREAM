package dream.edu.rpt.emplist.dao;

import java.util.List;

import common.bean.User;

import dream.edu.rpt.emplist.dto.EduRptEmpCommonDTO;

/**
 * �ڰ����з� - ��� dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface EduRptEmpListDAO
{
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     *
     * @param eduRptEmpCommonDTO
     * @return List
     */
    public List findList(EduRptEmpCommonDTO eduRptEmpCommonDTO    ,User user);

    public String findTotalCount(EduRptEmpCommonDTO eduRptEmpCommonDTO, User user);

}