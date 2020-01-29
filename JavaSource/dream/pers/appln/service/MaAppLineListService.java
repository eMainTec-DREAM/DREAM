package dream.pers.appln.service;

import java.util.List;

import common.bean.User;
import dream.pers.appln.dto.MaAppLineCommonDTO;

/**
 * 질의 - 목록 service
 * @author  kim21017
 * @version $Id: MaAppLineListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaAppLineListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaAppLineListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maAppLineCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findQnaList(MaAppLineCommonDTO maAppLineCommonDTO, User user);    

    /**
     * delete
     * @author kim21017
     * @version $Id: MaAppLineListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maAppLineDTOList
     * @return
     * @throws Exception
     */
    public int deleteQna(String[] deleteRows, User user) throws Exception;

    /**
     * 결재선 입력
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param deleteRows
     * @param user
     * @param maAppLineCommonDTO
     * @return 
     */
    public int insertLine(String[] deleteRows, User user, MaAppLineCommonDTO maAppLineCommonDTO);
    
    public String findTotalCount(MaAppLineCommonDTO maAppLineCommonDTO, User user);


}
