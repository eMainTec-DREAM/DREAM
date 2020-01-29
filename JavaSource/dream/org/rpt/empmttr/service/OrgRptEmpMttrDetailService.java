package dream.org.rpt.empmttr.service;

import java.util.List;

import common.bean.User;
import dream.org.rpt.empmttr.dto.OrgRptEmpMttrCommonDTO;
import dream.org.rpt.empmttr.dto.OrgRptEmpMttrDetailDTO;

/**
 * MTTR(담당자) 상세 목록
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface OrgRptEmpMttrDetailService
{     
    /**
     *  grid find
     * @author  youngjoo38
     * @version $Id:$
     * @param orgRptEmpMttrCommonDTO 
     * @since   1.0
     * 
     * @param orgRptEmpMttrDetailDTO
     * @param loginUser
     * @throws Exception
     */
    public List findDetail(OrgRptEmpMttrCommonDTO orgRptEmpMttrCommonDTO, OrgRptEmpMttrDetailDTO orgRptEmpMttrDetailDTO, User loginUser);

    public String findTotalCount(OrgRptEmpMttrCommonDTO orgRptEmpMttrCommonDTO, OrgRptEmpMttrDetailDTO orgRptEmpMttrDetailDTO, User user);
}
