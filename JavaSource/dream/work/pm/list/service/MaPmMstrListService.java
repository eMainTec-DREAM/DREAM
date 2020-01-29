package dream.work.pm.list.service;

import java.util.List;

import common.bean.User;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;

/**
 * 예방작업기준 - 목록 service
 * @author  jung7126
 * @version $Id: MaPmMstrListService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
 * @since   1.0
 */
public interface MaPmMstrListService
{     
    /**
     *  grid find
     * @author  jung7126
     * @version $Id: MaPmMstrListService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
     * @param maPmMstrCommonDTO
     * @since   1.0
     * 
     * @return 조회 결과 
     * @throws Exception
     */
    public List findList(MaPmMstrCommonDTO maPmMstrCommonDTO, User user);
    public List findWorkList(MaPmMstrCommonDTO maPmMstrCommonDTO, User user);
    public List findInsList(MaPmMstrCommonDTO maPmMstrCommonDTO, User user);
    public List findTrList(MaPmMstrCommonDTO maPmMstrCommonDTO, User user);
    public List findCalList(MaPmMstrCommonDTO maPmMstrCommonDTO, User user);
    public String findTotalCount(MaPmMstrCommonDTO maPmMstrCommonDTO, User user) throws Exception;
    
    /**
     * delete
     * @author jung7126
     * @version $Id: MaPmMstrListService.java,v 1.0 2015/12/02 09:12:40 jung7126 Exp $
     * @since   1.0
     * 
     * @param deleteRows
     * @param compNo
     * @return
     * @throws Exception
     */
    public int deleteList(String[] deleteRows, User user) throws Exception;

    public List copyPm(String[] selectRows, User loginUser)throws Exception;
	public int insertQrCode(String[] selectRows, String fileName, User loginUser)throws Exception;
	public int insertListQrCode(MaPmMstrCommonDTO maPmMstrCommonDTO, String fileName, User loginUser)throws Exception;
	public String getData(User user, MaPmMstrCommonDTO maPmMstrCommonDTO);

}
