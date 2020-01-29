package dream.org.rpt.empmttr.dao;

import java.util.List;

import common.bean.User;
import dream.org.rpt.empmttr.dto.OrgRptEmpMttrCommonDTO;

/**
 * MTTR(담당자) 목록 dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface OrgRptEmpMttrListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param orgRptEmpMttrCommonDTO
     * @param loginUser
     * @return List
     */
    public List findList(OrgRptEmpMttrCommonDTO orgRptEmpMttrCommonDTO, User loginUser);

    public String findTotalCount(OrgRptEmpMttrCommonDTO orgRptEmpMttrCommonDTO, User user);
    
}