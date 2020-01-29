package dream.ass.base.dao;

import common.bean.User;
import dream.ass.base.dto.AssBasePointValDetailDTO;


/**
 * 메뉴 - 목록 dao
 * @author  youngjoo38
 * @version $Id: AssBasePointValDetailDAO.java,v 1.0 2017/11/06 16:00:12 youngjoo38 Exp $
 * @since   1.0
 */
public interface AssBasePointValScriptDAO 
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id: AssBasePointValDetailDAO.java,v 1.0 2017/11/06 16:00:12 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param assBasePointValDetailDTO
     * @return List
     */
    public int updateScript(AssBasePointValDetailDTO assBasePointValDetailDTO, User loginUser);

	public AssBasePointValDetailDTO findScript(AssBasePointValDetailDTO assBasePointValDetailDTO);
  
}