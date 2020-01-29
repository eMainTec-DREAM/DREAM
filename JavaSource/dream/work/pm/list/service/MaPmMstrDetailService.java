package dream.work.pm.list.service;

import common.bean.User;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.MaPmMstrDetailDTO;

/**
 * �� service
 * 
 * @author jung7126
 * @version $Id: MaPmMstrDetailService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
 * @since 1.0
 */
public interface MaPmMstrDetailService
{    
    /**
     * detail find
     * @author jung7126
     * @version $Id: MaPmMstrDetailService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
     * @since   1.0
     * 
     * @param eqCtgId
     * @param compNo
     * @return
     * @throws Exception
     */
	public MaPmMstrDetailDTO findDetail(MaPmMstrCommonDTO maPmMstrCommonDTO, User user)throws Exception;
    /**
     * detail insert
     * @author jung7126
     * @version $Id: MaPmMstrDetailService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
     * @since   1.0
     * 
     * @param maPmMstrDetailDTO
     * @return
     * @throws Exception
     */
    public int insertDetail(MaPmMstrDetailDTO maPmMstrDetailDTO, User user) throws Exception;
    /**
     * detail update
     * @author jung7126
     * @version $Id: MaPmMstrDetailService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
     * @since   1.0
     * 
     * @param maPmMstrDetailDTO
     * @return
     * @throws Exception
     */
    public int updateDetail(MaPmMstrDetailDTO maPmMstrDetailDTO, User user) throws Exception;
    /**
     * ���� �ִ��� ������ Ȯ��
     * @author kim21017
     * @version $Id: MaPmMstrDetailService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
     * @since   1.0
     * @param maPmMstrDetailDTO
     * @return
     * @throws Exception
     */
    public String checkDetail(MaPmMstrDetailDTO maPmMstrDetailDTO, User user) throws Exception;
    public int insertRevisionHistAndUpdateMstr(MaPmMstrDetailDTO maPmMstrDetailDTO, User user) throws Exception;
    
    /**
     * �ֱ�#�� ����Ǿ����ÿ� �ߺ��Ǵ��� üũ
     * @author js.lee
     * @since   1.0
     *
     * @param maPmMstrDetailDTO
     * @param user
     * @return
     * @throws Exception
     */
    public String pmNoCheck(MaPmMstrDetailDTO maPmMstrDetailDTO, User user) throws Exception;
}
