package dream.part.rep.dao;

import java.util.List;

import common.bean.User;
import dream.part.rep.dto.LovPtAppListDTO;

/**
 * 수리기안품의서 팝업
 * @author  ssong
 * @version $Id: $
 * @since   1.0
 */
public interface LovPtAppListDAO
{
    /**
     * 수리기안품의서 검색
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param lovPtAppListDTO
     * @param loginUser
     * @return
     */
    public List findPtAppList(LovPtAppListDTO lovPtAppListDTO, User loginUser);
}