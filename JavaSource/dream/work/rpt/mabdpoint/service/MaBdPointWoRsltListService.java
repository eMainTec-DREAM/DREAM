package dream.work.rpt.mabdpoint.service;

import java.util.List;

import common.bean.User;
import dream.work.rpt.mabdpoint.dto.MaBdPointCommonDTO;
import dream.work.rpt.mabdpoint.dto.MaBdPointWoRsltListDTO;

/**
 * 이상점검조치 - 작업결과 목록 service
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
     * @return 조회 결과 
     * @throws Exception
     */
    public List findList(MaBdPointCommonDTO maBdPointCommonDTO,MaBdPointWoRsltListDTO maBdPointWoRsltListDTO, User user);
    
    /**
     * 삭제
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
