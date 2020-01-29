package dream.edu.list.dao;

import java.util.List;

import common.bean.User;

import dream.edu.list.dto.EduCommonDTO;
import dream.edu.list.dto.EduEmpListDTO;

/**
 * 자격증 사원 목록 dao
 * @author  kim21017
 * @version $Id: EduEmpListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface EduEmpListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: EduEmpListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     *
     * @param eduCommonDTO
     * @param eduEmpListDTO
     * @param loginUser
     * @return List
     */
    public List findEmpList(EduCommonDTO eduCommonDTO, EduEmpListDTO eduEmpListDTO, User loginUser);

    /**
     * delete
     * @author kim21017
     * @version $Id: EduEmpListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param id
     * @param compNo
     * @return
     */
    public int deleteEmpList(String id, User loginUser);
    
    public String findTotalCount(EduCommonDTO eduCommonDTO, EduEmpListDTO eduEmpListDTO, User user);
}