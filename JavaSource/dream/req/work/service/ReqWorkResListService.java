package dream.req.work.service;

import java.util.List;

import common.bean.User;
import dream.ass.asset.dto.AssAssetCommonDTO;
import dream.req.work.dto.ReqWorkCommonDTO;
import dream.req.work.dto.ReqWorkResListDTO;

/**
 * 목록 service
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 */
public interface ReqWorkResListService
{
    /**
     *  grid find
     * @author  kim21017
     * @version $Id:$
     * @param reqWorkCommonDTO
     * @since   1.0
     *
     * @return 조회 결과
     * @throws Exception
     */
    public List findList(ReqWorkCommonDTO reqWorkCommonDTO,ReqWorkResListDTO reqWorkResListDTO, User user);

    /**
     * 삭제
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     *
     * @param deleteRows
     * @param compNo
     * @return
     */
    public int deleteList(String[] deleteRows, String compNo);

	/**
	 * Linked W/O
	 * @param reqWorkCommonDTO
	 * @param user
	 * @throws Exception
	 */
	public void linkWo(ReqWorkCommonDTO reqWorkCommonDTO, User user) throws Exception;

	public String findTotalCount(ReqWorkCommonDTO reqWorkCommonDTO,ReqWorkResListDTO reqWorkResListDTO, User user) throws Exception;

}
