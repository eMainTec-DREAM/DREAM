package dream.work.rpt.mabdpoint.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.mabdpoint.dto.MaBdPointCommonDTO;
import dream.work.rpt.mabdpoint.dto.MaBdPointWoRsltListDTO;

/**
 * �̻�������ġ - �۾���� ��� service
 * @author  syyang
 * @version $Id:$
 * @since   1.0
 */
public interface MaBdPointWoRsltListService
{     
    /**
     *  grid find
     * @author  syyang
     * @version $Id:$
     * @since   1.0
     * 
     * @param maBdPointCommonDTO
     * @param maBdPointWoRsltListDTO
     * @param user
     * @return ��ȸ ��� 
     * @throws Exception
     */
    public List findList(MaBdPointCommonDTO maBdPointCommonDTO,MaBdPointWoRsltListDTO maBdPointWoRsltListDTO, User user);
    
    /**
     * ����
     * @author  syyang
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
	 * @param maBdPointCommonDTO
	 * @param maBdPointWoRsltListDTO
	 * @param user 
	 * @throws Exception 
	 */
	public void linkWo(MaBdPointCommonDTO maBdPointCommonDTO,MaBdPointWoRsltListDTO maBdPointWoRsltListDTO, User user) throws Exception;
	public int insertWoNgPointRes(MaBdPointCommonDTO maBdPointCommonDTO,MaBdPointWoRsltListDTO maBdPointWoRsltListDTO, User user) throws Exception;
	
    public String findTotalCount(MaBdPointCommonDTO maBdPointCommonDTO,MaBdPointWoRsltListDTO maBdPointWoRsltListDTO, User user) throws Exception;

}
