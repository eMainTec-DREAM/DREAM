package dream.req.work.service;

import java.util.List;

import common.bean.User;
import dream.invt.list.dto.InvtDetailDTO;
import dream.req.work.dto.MaWoReqCommonDTO;
import dream.req.work.dto.MaWoReqResListDTO;
import dream.work.list.dto.MaWoResultMstrDetailDTO;

/**
 * 목록 service
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 */
public interface MaWoReqResListService
{     
    /**
     *  grid find
     * @author  kim21017
     * @version $Id:$
     * @param maWoReqCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findList(MaWoReqCommonDTO maWoReqCommonDTO,MaWoReqResListDTO maWoReqResListDTO, User user);
    
    /**
     * 삭제
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param deleteRows
     * @param compNo
     * @return 
     * @throws Exception 
     */
    public int deleteList(String[] deleteRows, User user) throws Exception;

	/**
	 * Linked W/O
	 * @param maWoReqCommonDTO
	 * @param user 
	 * @return 
	 * @throws Exception 
	 */
	public String linkWo(MaWoReqCommonDTO maWoReqCommonDTO, User user) throws Exception;
	public void linkWoPlan(MaWoReqCommonDTO maWoReqCommonDTO, User user) throws Exception;
	
	/**
     * 기존 투자 연결
     * @author  js.lee
     * @version $Id:$
     * @since   1.0
	 * 
	 * @param maWoReqCommonDTO
	 * @param user
	 * @throws Exception
	 */
	public void linkInvt(MaWoReqCommonDTO maWoReqCommonDTO, User user) throws Exception;
	
    public String findTotalCount(MaWoReqCommonDTO maWoReqCommonDTO,MaWoReqResListDTO maWoReqResListDTO, User user) throws Exception;
}
