package dream.edu.list.dao;

import common.bean.User;

import dream.edu.list.dto.EduCommonDTO;
import dream.edu.list.dto.EduEmpDetailDTO;

/**
 * 자격증 사원 상세 dao
 * @author  kim21017
 * @version $Id: EduEmpDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 */
public interface EduEmpDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: EduEmpDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param wkOrId
     * @param eduEmpId
     * @param compNo
     * @return
     */
    public EduEmpDetailDTO findDetail(String eduListId, User loginUser);

    /**
     * detail update
     * @author kim21017
     * @version $Id: EduEmpDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param eduEmpDetailDTO
     * @param eduCommonDTO
     * @return
     */
    public int updateDetail(EduEmpDetailDTO eduEmpDetailDTO, EduCommonDTO eduCommonDTO, User loginUser);

    /**
     * detail insert
     * @author kim21017
     * @version $Id: EduEmpDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param eduEmpDetailDTO
     * @param eduCommonDTO
     * @return
     */
    public int insertDetail(EduEmpDetailDTO eduEmpDetailDTO, EduCommonDTO eduCommonDTO, User loginUser);

    /**
     * 사원확인..
     */
    public String validEmp(EduEmpDetailDTO eduEmpDetailDTO, EduCommonDTO eduCommonDTO, User loginUser);
}