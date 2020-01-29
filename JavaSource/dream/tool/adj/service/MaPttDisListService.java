package dream.tool.adj.service;

import java.util.List;

import common.bean.User;
import dream.tool.adj.dto.MaPttDisCommonDTO;

/**
 * 질의 - 목록 service
 * @author  kim21017
 * @version $Id: MaPttDisListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaPttDisListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaPttDisListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @param maPttDisCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findDisList(MaPttDisCommonDTO maPttDisCommonDTO, User user);    

    /**
     * delete
     * @author kim21017
     * @version $Id: MaPttDisListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPttDisDTOList
     * @return
     * @throws Exception
     */
    public int deleteDis(String compNo, String[] deleteRows) throws Exception;

	public String findTotalCount(MaPttDisCommonDTO maPttDisCommonDTO, User user);


}
