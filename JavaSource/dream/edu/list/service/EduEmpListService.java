package dream.edu.list.service;

import java.util.List;

import common.bean.User;

import dream.edu.list.dto.EduCommonDTO;
import dream.edu.list.dto.EduEmpListDTO;

/**
 * 자격증 사원  목록
 * @author  kim21017
 * @version $Id: EduEmpListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface EduEmpListService
{
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: EduEmpListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     *
     * @param eduCommonDTO
     * @param eduEmpListDTO
     * @param loginUser
     * @throws Exception
     */
    public List findEmpList(EduCommonDTO eduCommonDTO, EduEmpListDTO eduEmpListDTO, User loginUser);
    /**
     *  delete
     * @author  kim21017
     * @version $Id: EduEmpListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     *
     * @param deleteRows
     * @param compNo
     * @throws Exception
     */
    public int deleteEmpList(String[] deleteRows, User loginUser) throws Exception;
    
    /**
     * find Total Count
     * @author  syyang
     * @version $Id:$
     * @since   1.0
     * 
     * @param eduCommonDTO
     * @param eduEmpListDTO
     * @return
     */
    public String findTotalCount(EduCommonDTO eduCommonDTO, EduEmpListDTO eduEmpListDTO, User user);

}
