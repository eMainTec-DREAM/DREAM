package dream.edu.list.service;

import common.bean.User;

import dream.edu.list.dto.EduCommonDTO;
import dream.edu.list.dto.EduEmpDetailDTO;

/**
 * 자격증 사원상세
 * @author  kim210117
 * @version $Id: EduEmpDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface EduEmpDetailService
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: EduEmpDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     *
     * @param wkOrId
     * @param eduEmpId
     * @param compNo
     * @return
     * @throws Exception
     */
    public EduEmpDetailDTO findDetail(String eduListId, User loginUser)throws Exception;
    /**
     * detail update
     * @author kim21017
     * @version $Id: EduEmpDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     *
     * @param eduEmpDetailDTO
     * @param eduCommonDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(EduEmpDetailDTO eduEmpDetailDTO, EduCommonDTO eduCommonDTO, User loginUser) throws Exception;
    /**
     * detail insert
     * @author kim21017
     * @version $Id: EduEmpDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     *
     * @param eduEmpDetailDTO
     * @param eduCommonDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(EduEmpDetailDTO eduEmpDetailDTO, EduCommonDTO eduCommonDTO, User loginUser) throws Exception;

    /**
     * 사원중복검사
     */
    public String validEmp(EduEmpDetailDTO eduEmpDetailDTO, EduCommonDTO eduCommonDTO,User loginUser);
}
