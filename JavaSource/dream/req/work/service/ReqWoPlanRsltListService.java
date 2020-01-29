package dream.req.work.service;

import java.util.List;

import common.bean.User;
import dream.req.work.dto.MaWoReqCommonDTO;
import dream.req.work.dto.ReqWoPlanRsltListDTO;

/**
 * ��� service
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface ReqWoPlanRsltListService
{     
    /**
     *  grid find
     * @author  youngjoo38
     * @version $Id:$
     * @param maWoReqCommonDTO
     * @since   1.0
     * 
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findList(MaWoReqCommonDTO maWoReqCommonDTO,ReqWoPlanRsltListDTO reqWoPlanRsltListDTO, User user);
    
    /**
     * ����
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
	 * @param maWoReqCommonDTO
	 * @param user 
	 * @throws Exception 
	 */
	public void linkWo(MaWoReqCommonDTO maWoReqCommonDTO, ReqWoPlanRsltListDTO reqWoPlanRsltListDTO, User user) throws Exception;
	
    public String findTotalCount(MaWoReqCommonDTO maWoReqCommonDTO,ReqWoPlanRsltListDTO reqWoPlanRsltListDTO, User user) throws Exception;

}
