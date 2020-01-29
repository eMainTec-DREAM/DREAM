package dream.org.rpt.empmttr.service;

import java.util.List;

import common.bean.User;
import dream.org.rpt.empmttr.dto.OrgRptEmpMttrCommonDTO;

/**
 * 예방점검 이행율(담당자) 목록
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface OrgRptEmpMttrListService
{     
    /**
     *  grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param orgRptEmpMttrCommonDTO
     * @param loginUser
     * @throws Exception
     */
    public List findList(OrgRptEmpMttrCommonDTO orgRptEmpMttrCommonDTO, User loginUser);

    public String findTotalCount(OrgRptEmpMttrCommonDTO orgRptEmpMttrCommonDTO, User user);
    
}
