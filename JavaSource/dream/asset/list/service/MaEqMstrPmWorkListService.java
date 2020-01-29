package dream.asset.list.service;

import java.util.List;

import common.bean.User;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrPmWorkListDTO;

/**
 * 설비 정기작업 목록
 * @author  kim21017
 * @version $Id: MaEqMstrPmWorkListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaEqMstrPmWorkListService
{   
	/**
	 * 설비 예방작업 목록 검색
	 * @param maEqMstrCommonDTO
	 * @param maEqMstrPmWorkListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public List findEqPmWorkList(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPmWorkListDTO maEqMstrPmWorkListDTO, User user) throws Exception;
    /**
     * 설비 예방작업 삭제
     * @param deletePmIdRows
     * @param deletePmEquipIdRows
     * @param compNo
     * @return
     * @throws Exception
     */
    public int deleteEqPmWorkList(String[] deletePmIdRows, String[] deletePmEquipIdRows, User user) throws Exception;
	/**
	 * 설비 예방작업 목록 개수 COUNT
	 * @param maEqMstrCommonDTO
	 * @param maEqMstrPmWorkListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPmWorkListDTO maEqMstrPmWorkListDTO, User user) throws Exception;

}
