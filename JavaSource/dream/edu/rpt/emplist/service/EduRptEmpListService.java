package dream.edu.rpt.emplist.service;

import java.util.List;

import common.bean.User;
import dream.edu.rpt.emplist.dto.EduRptEmpCommonDTO;

/**
 * 자격증분류 - 목록 service
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public interface EduRptEmpListService
{
    /**
     *  grid find
     * @author  ssong
     * @version $Id:$
     * @param eduRptEmpCommonDTO
     * @since   1.0
     *
     * @return 조회 결과
     * @throws Exception
     */
    public List findList(EduRptEmpCommonDTO eduRptEmpCommonDTO    ,User user);

    public String findTotalCount(EduRptEmpCommonDTO eduRptEmpCommonDTO, User user);

}
