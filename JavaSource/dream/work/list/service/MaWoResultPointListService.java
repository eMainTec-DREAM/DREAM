package dream.work.list.service;

import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultPointListDTO;

/**
 * 작업결과 검사항목  목록
 * @author  kim21017
 * @version $Id: MaWoResultPointListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaWoResultPointListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id: MaWoResultPointListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrCommonDTO
     * @param maWoResultPointListDTO
     * @param loginUser
     * @throws Exception
     */
    public List findPointList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultPointListDTO maWoResultPointListDTO, User loginUser);
    /**
     *  delete
     * @author  kim21017
     * @version $Id: MaWoResultPointListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @param compNo
     * @throws Exception
     */
    public int deletePointList(String[] deleteRows, User user) throws Exception;

	/**
	 * Save List
	 * @author  syyang
	 * @version $Id:$
	 * @since   1.0
	 * 
	 * @param gridList
	 * @param user
	 * @throws Exception 
	 */
	public void savePointList(List<Map> gridList, User user) throws Exception;

	public String findTotalCount(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultPointListDTO maWoResultPointListDTO, User loginUser) throws Exception;
}
