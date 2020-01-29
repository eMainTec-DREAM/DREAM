package dream.asset.list.service;

import common.bean.User;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrPmWorkDetailDTO;
import dream.asset.list.dto.MaEqMstrPmWorkListDTO;

/**
 * ���� �����۾� ��
 * @author  kim21017
 * @version $Id: MaEqMstrPmWorkDetailService.java,v 1.0 2015/12/04 09:08:29 kim210117 Exp $
 * @since   1.0
 */
public interface MaEqMstrPmWorkDetailService
{    
	/**
	 * ���� �����۾� �� ��ȸ
	 * @param maEqMstrCommonDTO
	 * @param maEqMstrPmWorkListDTO
	 * @param user
	 * @return
	 * @throws Exception
	 */
    public MaEqMstrPmWorkDetailDTO findDetail(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPmWorkListDTO maEqMstrPmWorkListDTO, User user)throws Exception;
    /**
     * ���� �����۾� ���� 
     * @param maEqMstrCommonDTO
     * @param maEqMstrPmWorkDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int updateDetail(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPmWorkDetailDTO maEqMstrPmWorkDetailDTO, User user) throws Exception;
    /**
     * ���� �����۾� �Է�
     * @param maEqMstrCommonDTO
     * @param maEqMstrPmWorkDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public int insertDetail(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPmWorkDetailDTO maEqMstrPmWorkDetailDTO, User user) throws Exception;
}
