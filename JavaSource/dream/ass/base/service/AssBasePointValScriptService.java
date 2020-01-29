package dream.ass.base.service;

import common.bean.User;
import dream.ass.base.dto.AssBasePointValDetailDTO;


/**
 * 평가기준 - 상세 service
 * @author  youngjoo38
 * @version $Id: AssBasePointValDetailService.java,v 1.0 2017/11/06 16:00:40 youngjoo38 Exp $
 * @since   1.0
 */
public interface AssBasePointValScriptService
{     
	public AssBasePointValDetailDTO findScript(AssBasePointValDetailDTO assBasePointValDetailDTO);    
	
    /**
     *  grid find
     * @author  youngjoo38
     * @version $Id: AssBasePointValDetailService.java,v 1.0 2017/11/06 16:00:40 youngjoo38 Exp $
     * @param assBasePointValDetailDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public int updateScript(AssBasePointValDetailDTO assBasePointValDetailDTO, User loginUser);    


}
