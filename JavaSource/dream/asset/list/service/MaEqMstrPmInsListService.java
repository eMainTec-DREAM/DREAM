package dream.asset.list.service;

import java.util.List;

import common.bean.User;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrPmInsListDTO;

/**
 * ���� �������� ���
 * @author  kim21017
 * @version $Id: MaEqMstrPmInsListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaEqMstrPmInsListService
{   
	/**
	 * ���� �������� ��� �˻�
	 * @param maEqMstrCommonDTO
	 * @param maEqMstrPmInsListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public List findEqPmInsList(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPmInsListDTO maEqMstrPmInsListDTO, User user) throws Exception;
    /**
     * ���� �������� ����
     * @param deletePmIdRows
     * @param deletePmEquipIdRows
     * @param compNo
     * @return
     * @throws Exception
     */
    public int deleteEqPmInsList(String[] deletePmIdRows, String[] deletePmEquipIdRows, User user) throws Exception;
	/**
	 * ���� �������� ��� ���� COUNT
	 * @param maEqMstrCommonDTO
	 * @param maEqMstrPmInsListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public String findTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPmInsListDTO maEqMstrPmInsListDTO, User user) throws Exception;

}
