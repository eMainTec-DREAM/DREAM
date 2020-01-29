package dream.req.work.service;

import java.util.List;

import common.bean.User;
import dream.req.work.dto.MaWoReqCommonDTO;
import dream.req.work.dto.ReqWoInvtRsltListDTO;

/**
 * ��� service
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public interface ReqWoInvtRsltListService
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
    public List findList(MaWoReqCommonDTO maWoReqCommonDTO,ReqWoInvtRsltListDTO reqWoInvtRsltListDTO, User user);
    
    /**
     * ����
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param deleteRows
     * @param compNo
     * @return 
     * @throws Exception 
     */
    public int deleteList(String[] deleteRows, User user);

	/**
	 * Linked W/O
	 * @param maWoReqCommonDTO
	 * @param user 
	 * @throws Exception 
	 */
	public void linkWo(MaWoReqCommonDTO maWoReqCommonDTO, ReqWoInvtRsltListDTO reqWoInvtRsltListDTO, User user) throws Exception;
	
	/**
     * ���� ���� ����
     * @author  js.lee
     * @version $Id:$
     * @since   1.0
	 * 
	 * @param maWoReqCommonDTO
	 * @param user
	 * @throws Exception
	 */
	public void linkInvt(MaWoReqCommonDTO maWoReqCommonDTO, ReqWoInvtRsltListDTO reqWoInvtRsltListDTO, User user) throws Exception;
	
    public String findTotalCount(MaWoReqCommonDTO maWoReqCommonDTO,ReqWoInvtRsltListDTO reqWoInvtRsltListDTO, User user) throws Exception;

}
