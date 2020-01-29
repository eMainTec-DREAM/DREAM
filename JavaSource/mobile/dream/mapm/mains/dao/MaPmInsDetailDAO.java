package mobile.dream.mapm.mains.dao;

import common.bean.User;
import mobile.dream.mapm.mains.dto.MaPmInsDetailDTO;

/**
 * �� dao
 * @author jung7126
 * @version $Id: MaPmInsDetailDAO.java,v 1.0 2015/12/02 08:25:47 jung7126 Exp $
 * @since 1.0
 */
public interface MaPmInsDetailDAO
{
    /**
     * detail find
     * @author jung7126
     * @version $Id: MaPmInsDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param eqCtgId
     * @param compNo
     * @return
     */
    public MaPmInsDetailDTO findDetail(String pmId, User user);
    
    /**
     * detail insert
     * @author jung7126
     * @version $Id: MaPmInsDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param maPmInsDetailDTO
     * @return
     */
    public int insertDetail(MaPmInsDetailDTO maPmInsDetailDTO);
    /**
     * detail update
     * @author jung7126
     * @version $Id: MaPmInsDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param maPmInsDetailDTO
     * @return
     */
    public int updateDetail(MaPmInsDetailDTO maPmInsDetailDTO) throws Exception;

    public int updateWorkOrder(MaPmInsDetailDTO maPmInsDetailDTO) throws Exception;

    /**
     * ���ó�¥ ���� wo�̹��� ������ ����
     * @author jung7126
     * @version $Id: MaPmInsDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param maPmInsDetailDTO
     * @return
     */
    public int deletePmSched(MaPmInsDetailDTO maPmInsDetailDTO) throws Exception;
    
    public int createPmSchedule(MaPmInsDetailDTO maPmInsDetailDTO) throws Exception;

    public int createWorkOrder(MaPmInsDetailDTO maPmInsDetailDTO) throws Exception;

    public String checkDetail(MaPmInsDetailDTO maPmInsDetailDTO, User user);
}